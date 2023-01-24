package me.cwpark.baedal.infra.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final AuthenticationManager authenticationManager;

	@Override
	public void configure(HttpSecurity http) {
		JwtAuthenticateFilter jwtAuthenticateFilter = new JwtAuthenticateFilter(authenticationManager);

		http.addFilterBefore(jwtAuthenticateFilter, LogoutFilter.class);
	}
}
