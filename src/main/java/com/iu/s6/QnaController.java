package com.iu.s6;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.board.BoardDTO;
import com.iu.board.qna.QnaDAO;
import com.iu.board.qna.QnaDTO;
import com.iu.board.qna.QnaService;
import com.iu.util.Pager;

@Controller
@RequestMapping("/qna/**")
public class QnaController {
	
	@Inject
	private QnaService qnaService;
	
	//list
	@RequestMapping(value="qnaList")
	public ModelAndView list(Model model, Pager pager) throws Exception {
		ModelAndView mv = qnaService.list(pager);
		mv.addObject("board", "qna");
		return mv;
	}
	
	//select
	@RequestMapping(value="qnaSelect")
	public ModelAndView select(Model model, int num, RedirectAttributes rd) throws Exception {
		ModelAndView mv = qnaService.select(num);
		return mv;
	}
	
	//write Form
	@RequestMapping(value="qnaWrite", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("board", "qna");
		return "board/boardWrite";
	}
	
	//write process
	@RequestMapping(value="qnaWrite", method=RequestMethod.POST)
	public ModelAndView write(BoardDTO boardDTO,List<MultipartFile> f1, HttpSession session) throws Exception {
		String realPath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(realPath);
		
		ModelAndView mv = qnaService.insert(boardDTO, f1, session);
		
		return mv;
	}
	
	//update Form
	@RequestMapping(value="qnaUpdate", method=RequestMethod.GET)
	public ModelAndView update(Model model, int num) throws Exception {
		ModelAndView mv = qnaService.select(num);
		mv.setViewName("board/boardUpdate");
		return mv;
	}
	
	//update process
	@RequestMapping(value="qnaUpdate", method=RequestMethod.POST)
	public ModelAndView update(BoardDTO boardDTO,List<MultipartFile> f1, HttpSession session) throws Exception {
		 ModelAndView mv=qnaService.update(boardDTO, f1, session);
		 return mv;
	}
	
	//delete process
	@RequestMapping(value="qnaDelete", method=RequestMethod.POST)
	public ModelAndView delete(int num, HttpSession session) throws Exception {
		
		ModelAndView mv=qnaService.delete(num, session);
		return mv;
	}
	
	//reply Form
	@RequestMapping(value="qnaReply", method=RequestMethod.GET)
	public String reply(Model model, int num) {
		model.addAttribute("board", "qna");
		model.addAttribute("num", num);
		return "board/boardReply";
	}
	//reply process
	@RequestMapping(value="qnaReply", method=RequestMethod.POST)
	public String reply(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.reply(qnaDTO);
		return "redirect:./qnaSelect?num="+qnaDTO.getNum();
	}

}
