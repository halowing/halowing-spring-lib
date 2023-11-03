package com.halowing.lib.spring.security;

public interface UserService {

	
	/**
	 * do process after succeeding login.
	 * @param username
	 */
	void loginSuccess(String username);
	
	
	/**
	 * 계정을 잠그는 로직 실행
	 * 
	 * @param username
	 */
	void lockAccount(String username);
	
}
