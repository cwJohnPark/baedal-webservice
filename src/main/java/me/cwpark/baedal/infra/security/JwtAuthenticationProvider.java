package me.cwpark.baedal.infra.security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtAuthenticationToken token = toJwtAuthentication(authentication);
		Claims claims = getClaims(token);

		List<GrantedAuthority> authorities = JwtTokenProvider.getAuthorities(claims);

		return new JwtAuthenticationToken(authorities, claims.getSubject(), "");
	}

	private Claims getClaims(JwtAuthenticationToken token) {
		return jwtTokenProvider.getClaims(token.getToken());
	}

	private JwtAuthenticationToken toJwtAuthentication(Authentication authentication) {
		return (JwtAuthenticationToken) authentication;
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return JwtAuthenticationToken.class.isAssignableFrom(authenticationType);
	}
}