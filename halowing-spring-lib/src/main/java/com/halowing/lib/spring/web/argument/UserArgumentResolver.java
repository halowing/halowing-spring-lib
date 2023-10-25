package com.halowing.lib.spring.web.argument;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.halowing.lib.spring.security.User;
import com.halowing.lib.spring.security.UserService;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {
	
	private final UserService userService;

	public UserArgumentResolver(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isUserClass = User.class.equals(parameter.getParameterType());
		return isUserClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		
		String userName = userDetails.getUsername();
		
		User user = userService.getUser(userName);
		
		return user;
	}

}
