package ru.blinov.control.inventory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {
	
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
		
	    ModelAndView mav = new ModelAndView();
	    
	    mav.addObject("exception", e);
	    mav.addObject("url", request.getRequestURL());
	    mav.setViewName(DEFAULT_ERROR_VIEW);
	    
	    return mav;
	}

}