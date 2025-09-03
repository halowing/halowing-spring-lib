package com.halowing.lib.spring.security;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 로그인한 User의 정보
 *
 */
public class LoginUser {

	@NotBlank
	private String username;
	
	private String password;
	
	private String name;
	
	@NotNull
	private Set<String> roles = new ConcurrentSkipListSet<String>();
	
	@URL
	private String imageUrl;
	
	@URL
	private String profileUrl;
	
	private LocalDateTime registDateTime;
	private LocalDateTime updateDateTime;
	
	private LocalDateTime accountExpiredDateTime;
	
	private LocalDateTime credentialUpdateDateTime;
	private LocalDateTime lastLoginDateTime;
	
	private int loginFailureCount = 0;
	
	private boolean enabled = true;
	private boolean locked  = false;
	
	
	private Integer credentialsExpiredLimit;
	private Integer lastLoginLimit;
	private Integer loginFailureLimit;
	
	public LoginUser() {}	
	
	public LoginUser(String username, String password, Set<String> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	@Override
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password + ", name=" + name + ", roles=" + roles
				+ ", imageUrl=" + imageUrl + ", profileUrl=" + profileUrl + ", registDateTime=" + registDateTime
				+ ", updateDateTime=" + updateDateTime + ", accountExpiredDateTime=" + accountExpiredDateTime
				+ ", credentialUpdateDateTime=" + credentialUpdateDateTime + ", lastLoginDateTime=" + lastLoginDateTime
				+ ", loginFailureCount=" + loginFailureCount + ", enabled=" + enabled + ", locked=" + locked
				+ ", credentialsExpiredLimit=" + credentialsExpiredLimit + ", lastLoginLimit=" + lastLoginLimit
				+ ", loginFailureLimit=" + loginFailureLimit + "]";
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getRegistDateTime() {
		return registDateTime;
	}

	public void setRegistDateTime(LocalDateTime registDateTime) {
		this.registDateTime = registDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public LocalDateTime getLastLoginDateTime() {
		return lastLoginDateTime;
	}

	public void setLastLoginDateTime(LocalDateTime lastLoginDateTime) {
		this.lastLoginDateTime = lastLoginDateTime;
	}

	public LocalDateTime getAccountExpiredDateTime() {
		return accountExpiredDateTime;
	}

	public void setAccountExpiredDateTime(LocalDateTime accountExpiredDateTime) {
		this.accountExpiredDateTime = accountExpiredDateTime;
	}

	public int getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(int loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getLoginFailureLimit() {
		return loginFailureLimit;
	}

	public Integer getCredentialsExpiredLimit() {
		return credentialsExpiredLimit;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Integer getLastLoginLimit() {
		return lastLoginLimit;
	}

	public LocalDateTime getCredentialUpdateDateTime() {
		return credentialUpdateDateTime;
	}

	public void setCredentialUpdateDateTime(LocalDateTime credentialUpdateDateTime) {
		this.credentialUpdateDateTime = credentialUpdateDateTime;
	}
	
	
}
