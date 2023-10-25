package com.halowing.lib.spring.security;

public interface UserService {

	LoginUser getLoginUser(String username);
	
	User getUser(String username);

	void lockAccount(String username);
	
}
