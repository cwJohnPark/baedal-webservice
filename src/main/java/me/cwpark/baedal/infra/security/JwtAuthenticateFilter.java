package me.cwpark.baedal.infra.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticateFilter extends OncePerRequestFilter {

	private final AuthService authService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String credentials = AuthorizationExtractor.extract(request);
		LoginMember loginMember = LoginMember.ANONYMOUS;

		if (Objects.nonNull(credentials)) {
			loginMember = authService.findMemberByToken(credentials);
		}

		SecurityContextHolder.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken(loginMember,
				"",
				loginMember.getAuthorities()));

		filterChain.doFilter(request, response);
	}
}