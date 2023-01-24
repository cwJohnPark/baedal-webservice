package me.cwpark.baedal.infra.security;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;

import me.cwpark.baedal.dto.ErrorMessageResponse;
import me.cwpark.baedal.infra.security.exception.InvalidAuthenticationException;
import me.cwpark.baedal.infra.security.exception.InvalidAuthorizationException;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = UserDetailsServiceAutoConfiguration.class)
public class SecurityConfig {

	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final ObjectMapper objectMapper;

	public SecurityConfig(AuthenticationManagerBuilder authenticationManagerBuilder,
		JwtAuthenticationProvider jwtAuthenticationProvider,
		ObjectMapper objectMapper) {

		this.authenticationManagerBuilder
			= authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
		this.objectMapper = objectMapper;
	}

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
			.hasAnyRole(Roles.ADMIN.name(), Roles.MEMBER.name());

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.apply(new JwtSecurityConfig(authenticationManagerBuilder.getOrBuild()));

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
