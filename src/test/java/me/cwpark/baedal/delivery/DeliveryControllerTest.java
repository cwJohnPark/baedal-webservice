package me.cwpark.baedal.delivery;

import static me.cwpark.baedal.auth.AuthAcceptanceFixture.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.restassured.filter.Filter;
import me.cwpark.baedal.acceptance.AcceptanceTest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;
import me.cwpark.baedal.infra.security.JwtTokenProvider;

@DisplayName("배달 관리 기능")
class DeliveryControllerTest extends AcceptanceTest {

	DeliveryAcceptanceStep step;

	String token;

	@BeforeEach
	void setup(@Autowired JwtTokenProvider jwtTokenProvider) {
		token = jwtTokenProvider.createToken(EMAIL);
		step = new DeliveryAcceptanceStep(client, token);

		배달_등록();
	}

	private void 배달_등록() {
		DeliveryFixtures.deliveries()
			.forEach(request -> step.등록_요청(request));
	}

	@Test
	void 배달_조회() {
		int duration = 3;

		List<DeliveryResponse> 배달_목록 = step.배달_목록_조회(duration, getDeliveriesDocument());

		DeliveryAcceptanceAssertions.목록_조회됨(배달_목록, DeliveryFixtures.deliveries());
	}

	private Filter getDeliveriesDocument() {
		return document("getDeliveries",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()),
			requestParameters(
				parameterWithName("duration").description("조회 범위")
			),
			responseFields(
				fieldWithPath("[]").description("배달 목록"))
				.andWithPrefix("[].",
					fieldWithPath("destination").description("배달 목적지 주소"),
					fieldWithPath("status").description("배달 상태"),
					fieldWithPath("orderedAt").description("주문 일자"))
		);
	}
}