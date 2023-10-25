package com.halowing.lib.spring.security;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SimpleUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private boolean accountNonExpired = false;
	private boolean accountNonLocked = false;
	private boolean credentialsNonExpired = false;
	private boolean enabled = false;
	
	@Override
	public String toString() {
		return "DefaultUserDetails [username=" + username + ", password=" + password + ", authorities=" + authorities
				+ ", accountNonExpired=" + accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNonExpired, accountNonLocked, authorities, credentialsNonExpired, enabled, password,
				username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleUserDetails other = (SimpleUserDetails) obj;
		return accountNonExpired == other.accountNonExpired && accountNonLocked == other.accountNonLocked
				&& Objects.equals(authorities, other.authorities)
				&& credentialsNonExpired == other.credentialsNonExpired && enabled == other.enabled
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
