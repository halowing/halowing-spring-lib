package com.halowing.lib.spring.web.security.exception;

import java.util.Set;

import org.springframework.http.HttpStatus;

import com.halowing.lib.spring.exception.ExceptionMessage;
import com.halowing.lib.spring.security.LoginUser;
import com.halowing.lib.spring.web.exception.AbstractSpringWebApplicationException;
import com.halowing.lib.spring.web.response.HttpMessageCode;

/**
 * 접근 권한 에러
 */
public class AuthorizedException extends AbstractSpringWebApplicationException {
	
	private static final long serialVersionUID = -5861687881317459988L;

	private static final String UNKNOWN_USER = "unknown user";
	
	private final HttpStatus httpStatus;
	private final String errorCode;
	private final LoginUser loginUser;
	
	private final String[] args;
	
	public AuthorizedException(LoginUser loginUser ) {
		super(ExceptionMessage.getMessage(HttpMessageCode.UNAUTHORIZED.getMessageCode(), getArgs(loginUser) ) );
		this.httpStatus = HttpStatus.UNAUTHORIZED;
		this.errorCode  = HttpMessageCode.UNAUTHORIZED.getMessageCode();
		this.loginUser = loginUser;
		this.args = getArgs(loginUser);
	}
	
	public AuthorizedException(String username, Set<String> roles ) {
		super(ExceptionMessage.getMessage(HttpMessageCode.UNAUTHORIZED.getMessageCode(), getArgs(username, roles) ) );
		this.httpStatus = HttpStatus.UNAUTHORIZED;
		this.errorCode  = HttpMessageCode.UNAUTHORIZED.getMessageCode();
		this.loginUser = getLoginUser(username, roles);
		this.args = getArgs(username, roles);
	}
	
	
	private static String[] getArgs(String username, Set<String> roles) {
		if(username == null || username.isBlank())
			return new String[]{UNKNOWN_USER};
		
		StringBuffer sb = new StringBuffer();
		
		sb.append(username);
		
		if(roles != null ) {
			
			for(String role : roles)
			{
				sb.append(",").append(role);
			}
		}
		return sb.toString().split(",");
	}
	
	private static LoginUser getLoginUser(String username, Set<String> roles) {
		LoginUser u = new LoginUser();
		
		if(username == null || username.isBlank())
			username = UNKNOWN_USER;
		
		u.setUsername(username);
		u.setRoles(roles);
		
		return u;
	}

	private static String[] getArgs(LoginUser loginUser) {
		
		if(loginUser == null)
			return new String[]{UNKNOWN_USER};
		
		StringBuffer sb = new StringBuffer();
		sb.append(loginUser.getUsername());
		
		if(loginUser.getRoles() != null) {
			for(String role : loginUser.getRoles()) {
				sb.append(",").append(role);
			}
		}
		
		return sb.toString().split(",");
	}
	
	public LoginUser getLoginUser() {
		return this.loginUser;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}


	@Override
	public String getErrorCode() {
		return this.errorCode;
	}


	@Override
	public String[] getArgs() {
		return this.args;
	}
}
