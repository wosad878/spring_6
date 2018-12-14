package com.iu.s6;

import java.awt.Dialog.ModalExclusionType;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.board.BoardDTO;
import com.iu.board.notice.NoticeService;
import com.iu.util.FileSaver;
import com.iu.util.Pager;

@Controller
@RequestMapping("/notice/**")
public class NoticeController {
	@Inject
	private NoticeService noticeService;
	
	
	//list
	@RequestMapping(value="noticeList")
	public ModelAndView list(Pager pager) throws Exception {
		ModelAndView mv = noticeService.list(pager);
		mv.addObject("board", "notice");
		throw new NullPointerException();
		//return mv;
	}
	
	//select
	@RequestMapping(value="noticeSelect")
	public ModelAndView select(int num) throws Exception {
		ModelAndView mv = noticeService.select(num);
		throw new NumberFormatException();
		//return mv;
	}
	
	//write Form
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	//write process
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public ModelAndView write(BoardDTO boardDTO, HttpSession session, List<MultipartFile> f1, RedirectAttributes rd) throws Exception {
		String realPath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(realPath);
		
		ModelAndView mv = noticeService.insert(boardDTO, f1, session);
		
		return mv;
	}
	
	//update Form
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public ModelAndView update(int num) throws Exception {
		ModelAndView mv = noticeService.select(num);
		mv.setViewName("board/boardUpdate");
		return mv;
	}  
	
	//update process
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public ModelAndView update(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session) throws Exception {
		 ModelAndView mv=noticeService.update(boardDTO, f1, session);
		 return mv;
	}
	
	//delete process
	@RequestMapping(value="noticeDelete", method=RequestMethod.POST)
	public ModelAndView delete(int num, HttpSession session) throws Exception {
		ModelAndView mv=noticeService.delete(num, session);
		return mv;
	}

}






