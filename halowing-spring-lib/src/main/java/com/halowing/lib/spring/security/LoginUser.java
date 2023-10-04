package com.halowing.lib.spring.security;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

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
	private Set<String> roles = new ConcurrentSkipListSet<String>();
	
	@URL
	private String imageUrl;
	
	@URL
	private String profileUrl;

	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", roles=" + roles + ", imageUrl=" + imageUrl + ", profileUrl="
				+ profileUrl + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(roles, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginUser other = (LoginUser) obj;
		return Objects.equals(roles, other.roles) && Objects.equals(username, other.username);
	}




	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
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
