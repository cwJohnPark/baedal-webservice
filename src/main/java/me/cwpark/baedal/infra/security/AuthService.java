package me.cwpark.baedal.infra.security;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.infra.security.exception.InvalidAuthenticationException;
import me.cwpark.baedal.member.Member;
import me.cwpark.baedal.member.MemberService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;

	public LoginMember findMemberByToken(String credentials) {
		if (!jwtTokenProvider.validateToken(credentials)) {
			throw new InvalidAuthenticationException();
		}

		String email = jwtTokenProvider.getPayload(credentials);
		Member member = memberService.findByEmail(email);
		return new LoginMember(member.getEmail(), member.getPassword().toValue());
	}
}
