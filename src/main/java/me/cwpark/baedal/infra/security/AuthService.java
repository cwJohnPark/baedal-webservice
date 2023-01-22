package me.cwpark.baedal.infra.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.auth.dto.TokenRequest;
import me.cwpark.baedal.auth.dto.TokenResponse;
import me.cwpark.baedal.infra.security.exception.InvalidAuthenticationException;
import me.cwpark.baedal.member.Member;
import me.cwpark.baedal.member.MemberService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	public LoginMember findMemberByToken(String credentials) {
		if (!jwtTokenProvider.validateToken(credentials)) {
			throw new InvalidAuthenticationException();
		}

		String email = jwtTokenProvider.getPayload(credentials);
		Member member = memberService.findByEmail(email);
		return new LoginMember(member.getEmail(), member.getPassword().toValue());
	}

	public TokenResponse login(TokenRequest tokenRequest) {
		Member member = memberService.findByEmail(tokenRequest.getEmail());

		validatePassword(tokenRequest, member);

		return TokenResponse.of(jwtTokenProvider.createToken(member.getEmail()));
	}

	private void validatePassword(TokenRequest tokenRequest, Member member) {
		if (member.isEqualPassword(passwordEncoder, tokenRequest.getPassword())) {
			throw new InvalidAuthenticationException();
		}
	}
}
