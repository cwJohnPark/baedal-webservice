package me.cwpark.baedal.auth;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import me.cwpark.baedal.acceptance.AcceptanceTestStep;
import me.cwpark.baedal.acceptance.RestAssuredClient;
import me.cwpark.baedal.auth.dto.TokenRequest;
import me.cwpark.baedal.auth.dto.TokenResponse;

public class AuthAcceptanceTestStep extends AcceptanceTestStep<TokenRequest, TokenResponse> {

	public AuthAcceptanceTestStep(RestAssuredClient client) {
		super(client, TokenResponse.class);
	}

	@Override
	protected String getRequestPath() {
		return "/login";
	}

	public ExtractableResponse<Response> 로그인_요청(TokenRequest request, RequestSpecification filter) {
		return 등록_요청(filter, request);
	}
}
