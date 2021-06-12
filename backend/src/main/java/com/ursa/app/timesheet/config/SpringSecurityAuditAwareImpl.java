package com.ursa.app.timesheet.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ursa.app.timesheet.security.UserDetailsImpl;

public class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {
	
	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null ||
				!authentication.isAuthenticated() ||
				authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}
      
		return Optional.of(((UserDetailsImpl) authentication.getPrincipal()).getUser().getId());
	}
}