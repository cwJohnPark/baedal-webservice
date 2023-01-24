package me.cwpark.baedal.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.auth.dto.TokenRequest;
import me.cwpark.baedal.auth.dto.TokenResponse;
import me.cwpark.baedal.auth.exception.LoginFailedException;
import me.cwpark.baedal.infra.security.JwtTokenProvider;
import me.cwpark.baedal.member.Member;
import me.cwpark.baedal.member.MemberService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final MemberService memberService;
	private final JwtTokenProvider jwtTokenProvider;
	private final PasswordEncoder passwordEncoder;

	public TokenResponse login(TokenRequest tokenRequest) {
		Member member = memberService.findByEmail(tokenRequest.getEmail());

		validatePassword(tokenRequest, member);

		return TokenResponse.of(jwtTokenProvider.createToken(member.getEmail()));
	}

	private void validatePassword(TokenRequest tokenRequest, Member member) {
		if (!member.isEqualPassword(passwordEncoder, tokenRequest.getPassword())) {
			throw new LoginFailedException();
		}
	}
}
