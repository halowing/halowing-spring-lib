package com.halowing.lib.spring.web.argument;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.halowing.lib.spring.security.LoginUser;
import com.halowing.lib.spring.security.UserService;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
	
	private final UserService userService;

	public LoginUserArgumentResolver(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isUserClass = LoginUser.class.equals(parameter.getParameterType());
		return isUserClass;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		
		String userName = userDetails.getUsername();
		
		LoginUser loginUser = userService.getLoginUser(userName);
		
		return loginUser;
	}

}
