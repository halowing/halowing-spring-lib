package com.halowing.lib.spring.web.exception.resolver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.halowing.lib.web.exception.WebApplicationException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WebApplicationExceptionResolver extends AbstractHandlerExceptionResolver {
	
	private static final Logger log = LoggerFactory.getLogger(WebApplicationExceptionResolver.class);
	
	@Autowired
	private LocaleResolver localeResolver;
	
	@Autowired
	private MessageSourceAccessor messageSourceAccessor;

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		if(ex instanceof WebApplicationException ) {
			WebApplicationException aex = (WebApplicationException) ex;
			return getModel(request, aex);
		}
		
		return null;
				
	}
	
	protected ModelAndView getModel(HttpServletRequest request, WebApplicationException  ex) {
		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.setStatus( HttpStatus.valueOf(ex.getHttpStatus()) );
		mav.addObject("status",ex.getHttpStatus());
		mav.addObject("error",getMessages(ex, localeResolver.resolveLocale(request)));
		mav.addObject("timestamp",LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		mav.addObject("path",request.getRequestURI());
		return mav;
	}
	
	protected List<String> getMessages(WebApplicationException ex, Locale locale){
		
		List<String> list = new ArrayList<>();
		String message = messageSourceAccessor.getMessage(ex.getErrorCode(), ex.getArgs(), locale);
		
		log.debug("Locale = {}, error message = {}", locale, message);
		list.add(message);
		
		return list;
	}
}
