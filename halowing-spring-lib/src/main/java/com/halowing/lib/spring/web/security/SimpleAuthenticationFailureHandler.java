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

	private static final String DEFAULT_LOGIN_URL = "/login.html";
	
	private final UserService userService;
	private final String loginUrl;

	public SimpleAuthenticationFailureHandler(
			UserService userService, 
			String loginUrl) {
		this.userService = userService;
		this.loginUrl = StringUtility.isBlank(loginUrl) ? DEFAULT_LOGIN_URL : loginUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		
		response.sendRedirect(request.getContextPath()+ loginUrl);
		
		String username = request.getParameter("username");
		log.debug("username = {}",username);
		
		if(StringUtility.isBlank(username))
			return;
		
		userService.lockAccount(username);
	}

}
