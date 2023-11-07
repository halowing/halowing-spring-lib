package com.halowing.lib.spring.web.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;

public class FieldErrorsSpringWebApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(FieldErrorsSpringWebApplicationException.class);
	private final Map<String, String> errorMap = new HashMap<String, String>();
	
	public FieldErrorsSpringWebApplicationException(List<FieldError> errorList) {
		super(getMessage(errorList));
		
		errorList.forEach(error -> {
			
			log.error("error = {}",error);
			
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
	}

	private static String getMessage(List<FieldError> errorList) {

		StringBuffer sb = new StringBuffer();
		errorList.forEach(error -> {
			
			if(sb.length()>0)
				sb.append(", ");
			
			sb.append(error.getField()).append(':').append(error.getDefaultMessage());
		});
		
		return "Field Error :"+sb.toString();
	}
	
	
	public Map<String,String> getErrorMap(){
		return errorMap;
	}

}
