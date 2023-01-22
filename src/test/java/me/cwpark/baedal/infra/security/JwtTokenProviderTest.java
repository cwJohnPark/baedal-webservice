package me.cwpark.baedal.infra.security;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JwtTokenProviderTest {

	JwtTokenProvider jwtTokenProvider;

	@BeforeEach
	void setUp() {
		jwtTokenProvider = new JwtTokenProvider("secretKey", 1_000);
	}

	@Test
	void createToken() {
		String token = jwtTokenProvider.createToken("test@mail.com");
		assertThat(token).isNotNull();
	}

	@Test
	void getPayload() {
		String email = "test@mail.com";
		String token = jwtTokenProvider.createToken(email);

		String payload = jwtTokenProvider.getPayload(token);

		assertThat(payload).isEqualTo(email);
	}

	@Test
	void validateToken() {
		String token = jwtTokenProvider.createToken("test@mail.com");

		assertThat(jwtTokenProvider.validateToken(token)).isTrue();
	}

	@Test
	void validateTokenInvalidToken() {
		String invalidToken = "test@mail.com";

		assertThat(jwtTokenProvider.validateToken(invalidToken)).isFalse();
	}
}