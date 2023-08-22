package com.halowing.lib.spring.security;

import java.util.Arrays;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

/**
 * 로그인한 User의 정보
 *
 */
@Validated
public class LoginUser {

	@NotBlank
	private String username;
	
	@NotNull
	private String[] roles;
	
	@URL
	private String imageUrl;
	
	@URL
	private String profileUrl;

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", roles=" + Arrays.toString(roles) + ", imageUrl=" + imageUrl
				+ ", profileUrl=" + profileUrl + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	
}
