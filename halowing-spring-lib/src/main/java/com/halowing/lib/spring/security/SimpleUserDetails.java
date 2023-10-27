package com.halowing.lib.spring.security;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SimpleUserDetails extends LoginUser implements UserDetails{

	private static final long serialVersionUID = 1L;
	
//	private static final String ROLE_PRIFIX = "ROLE_"; 
	
	private final String username;
	private final String password;
	private final Set<GrantedAuthority> authorities = new HashSet<>();
	private final boolean accountNonExpired;
	private final boolean accountNonLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;
	
	public SimpleUserDetails(LoginUser loginUser) {
		this.username = loginUser.getUsername();
		this.password = loginUser.getPassword();
		this.enabled  = loginUser.isEnabled();
		
		final LocalDateTime accountExpiredDateTime 			= loginUser.getAccountExpiredDateTime();
		
		final boolean locked								= loginUser.isLocked();
		final int loginFailureCount 						= loginUser.getLoginFailureCount();
		final LocalDateTime lastLoginDateTime 				= loginUser.getLastLoginDateTime();
		final LocalDateTime credentialUpdateDateTime		= loginUser.getCredentialUpdateDateTime();
		
		final Integer loginFailureLimit 					= loginUser.getLoginFailureLimit();
		final Integer credentialsExpiredLimit 				= loginUser.getCredentialsExpiredLimit();
		final Integer lastLoginLimit						= loginUser.getLastLoginLimit();
		
		
		setAccountExpiredDateTime(accountExpiredDateTime);
		setLocked(locked);
		setLastLoginDateTime(lastLoginDateTime);
		setCredentialUpdateDateTime(credentialUpdateDateTime);
		setLoginFailureCount(loginFailureCount);
		setName(loginUser.getName());
		setImageUrl(loginUser.getImageUrl());
		setProfileUrl(loginUser.getProfileUrl());
		setRegistDateTime(loginUser.getRegistDateTime());
		setUpdateDateTime(loginUser.getUpdateDateTime());
		setRoles(loginUser.getRoles());
		
		this.accountNonExpired = accountExpiredDateTime == null ? true : accountExpiredDateTime.isAfter(LocalDateTime.now()) ;
		this.accountNonLocked  = locked ? false
					: (loginFailureLimit != null && loginFailureCount > loginFailureLimit )? false 
					: (lastLoginLimit    != null && LocalDateTime.now().minusDays(lastLoginLimit).isAfter(lastLoginDateTime) )? false
					: true
						;
		this.credentialsNonExpired = credentialsExpiredLimit == null ? true 
				: credentialUpdateDateTime == null ? false
				: LocalDateTime.now().minusDays(credentialsExpiredLimit).isBefore(credentialUpdateDateTime)
				;
		final Set<String> roles = loginUser.getRoles();
		roles.forEach(role -> {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
//					ROLE_PRIFIX + 
					role);
			authorities.add(authority );
		});
		
	}
	
	@Override
	public String toString() {
		return "SimpleUserDetails [username=" + username + ", password=" + password + ", authorities=" + authorities
				+ ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
