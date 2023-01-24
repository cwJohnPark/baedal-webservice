package me.cwpark.baedal.infra.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.dto.ErrorMessageResponse;
import me.cwpark.baedal.infra.security.exception.InvalidAuthenticationException;
import me.cwpark.baedal.infra.security.exception.InvalidAuthorizationException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class SecurityConfig {

	private final JwtAuthenticateFilter jwtAuthenticateFilter;
	private final ObjectMapper objectMapper;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/members")
				.permitAll()
			.antMatchers(
				"/login",
				"/docs")
				.permitAll()
			.anyRequest()
			.authenticated();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticateFilter, UsernamePasswordAuthenticationFilter.class);

		http.formLogin().disable();

		http.exceptionHandling()
			.authenticationEntryPoint(((request, response, authException) -> {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				objectMapper.writeValue(
					response.getOutputStream(),
					ErrorMessageResponse.of(new InvalidAuthenticationException())
				);
			}))
			.accessDeniedHandler(((request, response, accessDeniedException) -> {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				objectMapper.writeValue(
					response.getOutputStream(),
					ErrorMessageResponse.of(new InvalidAuthorizationException())
				);
			}));

		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers(
			"/resources/**"
		);
	}
}
