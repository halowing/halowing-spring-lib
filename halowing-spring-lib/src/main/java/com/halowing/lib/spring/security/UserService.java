package com.halowing.lib.spring.security;

public interface UserService {

	LoginUser getLoginUser(String userId);
	
	User getUser(String userId);
}
