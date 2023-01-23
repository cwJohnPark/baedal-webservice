package me.cwpark.baedal.auth;

import static org.assertj.core.api.Assertions.*;

import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import me.cwpark.baedal.auth.dto.TokenResponse;

public class AuthAcceptanceAssertions {

	public static void 로그인_성공(ExtractableResponse<Response> 로그인_응답) {
		assertThat(로그인_응답.statusCode()).isEqualTo(HttpStatus.OK.value());

		assertThat(로그인_응답.as(TokenResponse.class))
			.extracting(TokenResponse::getAccessToken)
			.isNotNull();
	}

	public static void 로그인_실패(ExtractableResponse<Response> 로그인_응답) {
		assertThat(로그인_응답.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}
}
