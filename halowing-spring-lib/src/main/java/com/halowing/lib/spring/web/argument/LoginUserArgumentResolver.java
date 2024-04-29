package com.halowing.lib.spring.web.argument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.halowing.lib.spring.security.LoginUser;
import com.halowing.lib.spring.security.SimpleUserDetails;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
	
	private static final Logger log = LoggerFactory.getLogger(LoginUserArgumentResolver.class);	

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isUserClass = LoginUser.class.equals(parameter.getParameterType());
		return isUserClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("principal ={}",principal.toString());
		
		LoginUser loginUser  = null;
		
		if(principal instanceof SimpleUserDetails) {
			SimpleUserDetails userDetails = (SimpleUserDetails) principal;
			loginUser = new LoginUser(userDetails);
		} else if (principal instanceof String) {
			loginUser = new LoginUser();
			loginUser.setUsername((String) principal);
					
		} else {
			loginUser = new LoginUser();
			loginUser.setUsername("anonymousUser");
		}
		return loginUser;
	}

}
