package com.halowing.lib.spring.web.security.exception;

import org.springframework.http.HttpStatus;

import com.halowing.lib.spring.security.LoginUser;
import com.halowing.lib.spring.web.exception.AbstractSpringWebApplicationException;

/**
 * Login 실패 에러 처리
 */
public class AuthenticationException extends AbstractSpringWebApplicationException {

	private static final long serialVersionUID = -2486241735160690747L;
	
	public static final String UNAUTHENTICATED_ERROR_CODE = "http.status.unauthenticated";
	
	private static final String UNKNOWN_USER = "unknown user";
	
	private final HttpStatus httpStatus;
	private final String errorCode;
	private final LoginUser loginUser;
	
	private final String[] args;
	
	public AuthenticationException(LoginUser loginUser ) {
		super(getConstructMessage(UNAUTHENTICATED_ERROR_CODE,getArgs(loginUser) ) );
		this.httpStatus = HttpStatus.UNAUTHORIZED;
		this.errorCode  = UNAUTHENTICATED_ERROR_CODE;
		this.loginUser = loginUser;
		this.args = getArgs(loginUser);
	}
	
	public AuthenticationException(String username ) {
		super(getConstructMessage(UNAUTHENTICATED_ERROR_CODE, getArgs(username) ) );
		this.httpStatus = HttpStatus.UNAUTHORIZED;
		this.errorCode  = UNAUTHENTICATED_ERROR_CODE;
		this.loginUser = getLoginUser(username);
		this.args = getArgs(username);
	}
	
	
	private static String[] getArgs(String username) {
		if(username == null || username.isBlank())
			return new String[]{UNKNOWN_USER};
		
		return new String[] {username};
	}
	
	private static LoginUser getLoginUser(String username) {
		LoginUser u = new LoginUser();
		
		if(username == null || username.isBlank())
			username = UNKNOWN_USER;
		
		u.setUsername(username);
		
		return u;
	}

	private static String[] getArgs(LoginUser loginUser) {
		
		if(loginUser == null || loginUser.getUsername() == null || loginUser.getUsername().isBlank())
			return new String[]{UNKNOWN_USER};
		
		return new String[] {loginUser.getUsername()};
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
