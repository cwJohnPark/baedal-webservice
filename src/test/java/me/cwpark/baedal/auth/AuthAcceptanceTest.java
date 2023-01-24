package me.cwpark.baedal.auth;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.filter.Filter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import me.cwpark.baedal.acceptance.AcceptanceTest;
import me.cwpark.baedal.auth.dto.TokenRequest;

@DisplayName("인증 기능")
public class AuthAcceptanceTest extends AcceptanceTest {

	AuthAcceptanceTestStep step;

	@BeforeEach
	void setup() {
		step = new AuthAcceptanceTestStep(client);
	}

	@Test
	void 로그인_성공() {
		TokenRequest 토큰_요청 = TokenRequest.of(AuthAcceptanceFixture.EMAIL, AuthAcceptanceFixture.PASSWORD);

		ExtractableResponse<Response> 로그인_응답 = step.로그인_요청(
			토큰_요청,
			given.filter(loginSuccessDocument())
		);

		AuthAcceptanceAssertions.로그인_성공(로그인_응답);
	}

	@Test
	void 로그인_실패() {
		TokenRequest 토큰_요청 = TokenRequest.of(AuthAcceptanceFixture.EMAIL, AuthAcceptanceFixture.WRONG_PASSWORD);

		ExtractableResponse<Response> 로그인_응답 = step.로그인_요청(
			토큰_요청,
			given.filter(loginFailedDocument())
		);

		AuthAcceptanceAssertions.로그인_실패(로그인_응답);
	}

	private Filter loginSuccessDocument() {
		return document("loginSuccess",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()),
			requestFields(
				fieldWithPath("email").description("이메일"),
				fieldWithPath("password").description("비밀번호")
			),
			responseFields(
				fieldWithPath("accessToken").description("접근 토큰")
			)
		);
	}

	private Filter loginFailedDocument() {
		return document("loginFailed",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()),
			requestFields(
				fieldWithPath("email").description("이메일"),
				fieldWithPath("password").description("비밀번호")
			),
			responseFields(
				fieldWithPath("message").description("오류 메시지"),
				fieldWithPath("type").description("오류 타입")
			)
		);
	}
}
