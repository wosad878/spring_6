package com.iu.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.util.Pager;
@Transactional
public interface BoardService {
	
	public ModelAndView list(Pager pager) throws Exception; 
		
	public ModelAndView select(int num)throws Exception;
	
	public ModelAndView insert(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session) throws Exception; 
	
	public ModelAndView update(BoardDTO boardDTO, List<MultipartFile> f1, HttpSession session)throws Exception;
	
	public ModelAndView delete(int num, HttpSession session)throws Exception;

}
