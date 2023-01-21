package me.cwpark.baedal.member;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class AcceptanceAssertions {

	public static void 등록됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(CREATED.value());
	}
}
