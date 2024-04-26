package com.halowing.lib.spring.web.exception.resolver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.halowing.lib.spring.web.exception.AbstractSpringWebApplicationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpringWebApplicationExceptionResolver extends AbstractHandlerExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		if(ex instanceof AbstractSpringWebApplicationException ) {
			AbstractSpringWebApplicationException aex = (AbstractSpringWebApplicationException) ex;
			return getModel(request, aex);
		}
		
		return null;
				
	}
	
	protected ModelAndView getModel(HttpServletRequest request, AbstractSpringWebApplicationException  ex) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.setStatus(ex.getHttpStatus());
		mav.addObject("status",ex.getHttpStatus().value());
		mav.addObject("messages",getMessages(ex));
		mav.addObject("timestamp",LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		mav.addObject("path",request.getRequestURI());
		return mav;
	}
	
	protected List<String> getMessages(AbstractSpringWebApplicationException ex){
		
		List<String> list = new ArrayList<>();
		
		list.add(ex.getLocalizedMessage());
		
		return list;
	}
}
