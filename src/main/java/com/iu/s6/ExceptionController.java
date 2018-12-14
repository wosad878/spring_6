package com.iu.s6;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView ex1() { 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/error");
		mv.addObject("msg", "ControllerAdvice");
		return mv;
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView ex2() {return null;}
	
	@ExceptionHandler(SQLException.class)
	public ModelAndView ex3() {return null;}
}
