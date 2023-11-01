package com.halowing.lib.spring.web.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.halowing.lib.spring.security.UserService;
import com.halowing.lib.string.StringUtility;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private final static Logger log = LoggerFactory.getLogger(SimpleAuthenticationFailureHandler.class);

	private static final String DEFAULT_LOGIN_URL = "/login.html?error";
	
	private final UserService userService;
	private final String failureRedirectUrl;
	
	public SimpleAuthenticationFailureHandler(
			UserService userService) {
		this(userService, null);
	}

	public SimpleAuthenticationFailureHandler(
			UserService userService, 
			String failureRedirectUrl) {
		this.userService = userService;
		this.failureRedirectUrl = StringUtility.isBlank(failureRedirectUrl) ? DEFAULT_LOGIN_URL : failureRedirectUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String username = request.getParameter("username");
		log.debug("username = {}",username);
		
		if(!StringUtility.isBlank(username)){
			userService.lockAccount(username);
		}
		
		String url = request.getContextPath() + failureRedirectUrl;
		
		response.sendRedirect(url);
	}

}
