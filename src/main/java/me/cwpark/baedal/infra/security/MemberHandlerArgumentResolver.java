package me.cwpark.baedal.infra.security;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import me.cwpark.baedal.auth.AuthenticatedMember;
import me.cwpark.baedal.auth.LoginMember;
import me.cwpark.baedal.infra.SecurityArgumentResolver;

@Component
public class MemberHandlerArgumentResolver implements SecurityArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Objects.nonNull(parameter.getParameterAnnotation(AuthenticatedMember.class));
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		JwtAuthenticationToken token = getAuthentication();
		return new LoginMember(String.valueOf(token.getPrincipal()));
	}

	private static JwtAuthenticationToken getAuthentication() {
		return (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
	}
}
