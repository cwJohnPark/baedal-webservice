package me.cwpark.baedal.member;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;

import io.restassured.filter.Filter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import me.cwpark.baedal.acceptance.AcceptanceAssertions;
import me.cwpark.baedal.acceptance.AcceptanceTest;
import me.cwpark.baedal.member.dto.MemberRequest;

@DisplayName("회원 관리 기능")
public class MemberAcceptanceTest extends AcceptanceTest {

	MemberAcceptanceTestStep step;

	final String EMAIL = "cwpark@naver.com";
	final String NAME = "cwpark";
	final String PASSWORD = "123456AAAaaa";
	final String INVALID_PASSWORD = "123456789Aa";

	@BeforeEach
	void setup() {
		step = new MemberAcceptanceTestStep(client);
	}

	@Test
	void 회원가입_성공() {
		ExtractableResponse<Response> 등록_응답 = step.등록_요청(
			given.filter(memberRegisterDocument()),
			MemberRequest.of(EMAIL, NAME, PASSWORD)
			);

		AcceptanceAssertions.등록됨(등록_응답);
	}

	@Test
	void 회원가입_실패() {
		ExtractableResponse<Response> 등록_응답 = step.등록_요청(
			given.filter(memberRegisterFailedDocument()),
			MemberRequest.of(EMAIL, NAME, INVALID_PASSWORD)
			);

		AcceptanceAssertions.등록_실패(등록_응답);
	}

	private Filter memberRegisterFailedDocument() {
		return document("memberRegisterFailed",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()),
			requestFields(
				fieldWithPath("email").description("이메일"),
				fieldWithPath("name").description("이름"),
				fieldWithPath("password").description("비밀번호")
			),
			responseFields(
				fieldWithPath("message").description("오류 메시지"),
				fieldWithPath("type").description("오류 타입")
			)
		);
	}

	private static RestDocumentationFilter memberRegisterDocument() {
		return document("memberRegister",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()),
			requestFields(
				fieldWithPath("email").description("이메일"),
				fieldWithPath("name").description("이름"),
				fieldWithPath("password").description("비밀번호")
			),
			responseFields(
				fieldWithPath("email").description("이메일"),
				fieldWithPath("name").description("이름")
			)
		);
	}
}
