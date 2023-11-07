package com.halowing.lib.spring.web.exception.resolver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BindExceptionResolver extends AbstractHandlerExceptionResolver{

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		if(!(ex instanceof BindException)) {
			return null;
		}
		
		BindException bex = (BindException) ex;
		Map<String,String> validations = getMessages(bex);
		
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.setStatus(HttpStatus.BAD_REQUEST);
		mav.addObject("status",HttpStatus.BAD_REQUEST.value());
		mav.addObject("validations",validations);
		mav.addObject("timestamp",LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		mav.addObject("path",request.getRequestURI());
		
		return mav;
	}
	
	private Map<String,String>getMessages(BindException ex){
		
		Map<String,String> map = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->{
			map.put(error.getField(), error.getDefaultMessage());
		});
		
		return map;
	}

}
