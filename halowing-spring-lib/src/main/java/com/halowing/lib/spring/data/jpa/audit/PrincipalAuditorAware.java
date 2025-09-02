package com.halowing.lib.spring.data.jpa.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.halowing.lib.spring.security.SimpleUserDetails;

/**
 * Spring Data JPA 감사기능에서 @CreatedBy 와 @LastModifiedBy 사용시 사용자 이름을 추가해주기 위해 
 */
public class PrincipalAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username  = null;
		
		if(principal instanceof SimpleUserDetails) {
			SimpleUserDetails userDetails = (SimpleUserDetails) principal;
			username = userDetails.getUsername();
		} else if (principal instanceof String) {
			username = (String) principal;
					
		} else {
			username = "anonymousUser";
		}
		
		return Optional
				.ofNullable(username)
				;
	}

}
