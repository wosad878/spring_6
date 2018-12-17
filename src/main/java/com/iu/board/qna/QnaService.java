package com.iu.board.qna;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	@Inject
	private FileDAO fileDAO;
	
	@Override
	public ModelAndView list(Pager pager) throws Exception {
		ModelAndView mv = new ModelAndView();
		int totalCount=qnaDAO.totalCount(pager);
		//row
		pager.makeRow();
		//pageing
		pager.makePage(totalCount);
		
		mv.addObject("list", qnaDAO.list(pager));
		mv.addObject(pager);
		mv.setViewName("board/boardList");
		return mv;
	}

	@Override
	public ModelAndView select(int num) throws Exception {
		ModelAndView mv = new ModelAndView();
		//1. notice table
		 BoardDTO boardDTO = qnaDAO.select(num);
		
		//2. Files table
		 if(boardDTO != null) {
			 mv.setViewName("board/boardSelect");
			 mv.addObject("dto", boardDTO);
			 
		 }else {
			 mv.setViewName("redirect:./qnaList");
			 mv.addObject("msg", "글이 없습니다");
		 }
		 
		 mv.addObject("board", "qna");
		
		return mv;
	}

	@Override
	public ModelAndView insert(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session) throws Exception {
		//1. sequence num 가져오기
				int num = qnaDAO.getNum();
				
				//2. Notice Table에 insert
				boardDTO.setNum(num);
				int result=qnaDAO.insert(boardDTO);
				
				//transaction 처리
				if(result<1) {
					throw new Exception();
				}
				
				//3. HDD에 File Save
				FileSaver fs = new FileSaver();
				String realPath = session.getServletContext().getRealPath("resources/notice");
				
				for(MultipartFile multipartFile:f1) {
					if(multipartFile.isEmpty()) {
						continue;
					}
					String fname=fs.saveFile(realPath, multipartFile);
				//4. Files table insert
					FileDTO fileDTO = new FileDTO();
					fileDTO.setNum(num);
					fileDTO.setFname(fname);
					fileDTO.setOname(multipartFile.getOriginalFilename());
					fileDTO.setKind("n");
					result = fileDAO.insert(fileDTO);
					//transaction 
					if(result<1) {
						throw new Exception();
					}
					
				}
				ModelAndView mv = new ModelAndView();
				mv.setViewName("redirect:./qnaList");
				mv.addObject("msg", "Write Success");
				return mv;
	}

	@Override
	public ModelAndView update(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		int result = qnaDAO.update(boardDTO);
		
		if(result<1) {
			throw new Exception();
		}
		//HDD save
		FileSaver fs = new FileSaver();
		String realPath = session.getServletContext().getRealPath("resources/notice");
		for(MultipartFile multipartFile:f1) {
			String fname=fs.saveFile(realPath, multipartFile);
			FileDTO fileDTO = new FileDTO();
			fileDTO.setNum(boardDTO.getNum());
			fileDTO.setOname(multipartFile.getOriginalFilename());
			fileDTO.setFname(fname);
			fileDTO.setKind("n");
			result = fileDAO.insert(fileDTO);
			if(result<1) {
				throw new Exception();
			}
		}
		
		
		String msg = "Update Success";
		
		mv.setViewName("redirect:./qnaSelect?num="+boardDTO.getNum());
		mv.addObject("msg", msg);
		return mv;
	}

	@Override
	public ModelAndView delete(int num, HttpSession session) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		//1. notice Delete
		int result = qnaDAO.delete(num);
		if(result<1) {
			throw new Exception();
		}
		//2. HDD Delete 준비
		FileDTO fileDTO = new FileDTO();
		fileDTO.setNum(num);
		fileDTO.setKind("n");
		List<FileDTO> ar = fileDAO.list(fileDTO);
		
		//3. Files table Delete
		result=fileDAO.deleteAll(fileDTO);
		
		
		//4. HDD Delete 
		String realPath = session.getServletContext().getRealPath("resources/notice");
		for(FileDTO fileDTO2:ar) {
			File file = new File(realPath, fileDTO2.getFname());
			file.delete();
		}
		
		mv.setViewName("redirect:./qnaList");
		mv.addObject("msg", "Delete Success");
		return mv;
	}
	
	public int reply (QnaDTO qnaDTO) throws Exception{
		//부모의 ref, step, depth
		BoardDTO pDto = qnaDAO.select(qnaDTO.getNum());
		QnaDTO pQnaDTO = (QnaDTO)pDto;
		
		qnaDAO.replyUpdate(pQnaDTO);
		
		qnaDTO.setRef(pQnaDTO.getRef());
		qnaDTO.setStep(pQnaDTO.getStep()+1);
		qnaDTO.setDepth(pQnaDTO.getDepth()+1);
		
		
		return qnaDAO.reply(qnaDTO);
	}
}










