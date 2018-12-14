package com.iu.s6;

import java.util.Enumeration;
import java.util.Iterator;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.iu.file.FileService;
import com.iu.file.PhotoDTO;

@Controller
@RequestMapping(value="/file/**")
public class FileController {
	
	@Inject
	private FileService fileService;
	
	@RequestMapping(value="delete")
	public ModelAndView delete(int fnum) throws Exception {
		ModelAndView mv= fileService.delete(fnum);
		return mv;
	}
	
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public String se2(PhotoDTO photoDTO, HttpSession session) throws Exception {
		 
		String result =fileService.se2(photoDTO, session);
		
		return result;
	}

}
