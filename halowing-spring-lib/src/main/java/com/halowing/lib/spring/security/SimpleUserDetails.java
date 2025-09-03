package com.halowing.lib.spring.security;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;

/**
 * UserDetail service  구현체. UserDetailsService에서 사용.
 */
public class SimpleUserDetails extends User{

	private static final long serialVersionUID = 1L;
	
	public static final String ROLE_PRIFIX = "ROLE_"; 
	
	private final LoginUser loginUser;
	
	public SimpleUserDetails(LoginUser loginUser) {
		super(
				loginUser.getUsername(), 
				loginUser.getPassword(),
				loginUser.isEnabled(),
				credentialsNonExpired(loginUser),
				accountNonExpired(loginUser),
				accountNonLocked(loginUser),
				authorities( loginUser.getRoles()) 
				);
		this.loginUser = loginUser;
	}
	
	private static boolean accountNonLocked(LoginUser loginUser) {
		final LocalDateTime accountExpiredDateTime 			= loginUser.getAccountExpiredDateTime();
		return accountExpiredDateTime == null ? true : accountExpiredDateTime.isAfter(LocalDateTime.now()) ;
	}

	private static boolean accountNonExpired(LoginUser loginUser) {
		
		boolean locked								= loginUser.isLocked();
		Integer loginFailureLimit 					= loginUser.getLoginFailureLimit();
		int loginFailureCount 						= loginUser.getLoginFailureCount();
		Integer lastLoginLimit						= loginUser.getLastLoginLimit();
		
		LocalDateTime lastLoginDateTime 				= loginUser.getLastLoginDateTime();
		
		return locked ? false
				: (loginFailureLimit != null && loginFailureCount > loginFailureLimit )? false 
				: (lastLoginLimit    != null && LocalDateTime.now().minusDays(lastLoginLimit).isAfter(lastLoginDateTime) )? false
				: true
					;
	}

	private static boolean credentialsNonExpired(LoginUser loginUser) {
		Integer credentialsExpiredLimit 				= loginUser.getCredentialsExpiredLimit();
		LocalDateTime credentialUpdateDateTime		= loginUser.getCredentialUpdateDateTime();
		
		return credentialsExpiredLimit == null ? true 
				: credentialUpdateDateTime == null ? false
				: LocalDateTime.now().minusDays(credentialsExpiredLimit).isBefore(credentialUpdateDateTime)
				;
	}

	private static Set<GrantedAuthority> authorities(Set<String> roles) {
		Assert.notNull(roles, "Cannot pass a null String collection");
		Set<GrantedAuthority> authorities = new HashSet<>();
		roles.forEach(role -> {
			if (role != null) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(
						ROLE_PRIFIX + 
						role);
				authorities.add(authority );
			}
		});
		
		return authorities;
	}


	public LoginUser getLoginUser() {
		return loginUser;
	}

	@Override
	public String toString() {
		return "SimpleUserDetails [loginUser=" + loginUser.toString() + ", super()=" + super.toString() + "]";
	}
	
	
}
