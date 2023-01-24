package me.cwpark.baedal.delivery;

import static me.cwpark.baedal.auth.AuthAcceptanceFixture.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.restassured.filter.Filter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import me.cwpark.baedal.acceptance.AcceptanceTest;
import me.cwpark.baedal.delivery.dto.DeliveryRequest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;
import me.cwpark.baedal.infra.security.JwtTokenProvider;

@DisplayName("배달 관리 기능")
class DeliveryAcceptanceTest extends AcceptanceTest {

	DeliveryAcceptanceStep step;
	String token;
	List<DeliveryResponse> 등록된_배달_목록;

	@BeforeEach
	void setup(@Autowired JwtTokenProvider jwtTokenProvider) {
		token = jwtTokenProvider.createToken(EMAIL);
		step = new DeliveryAcceptanceStep(client, token);

		등록된_배달_목록 = 배달_등록();
	}

	private List<DeliveryResponse> 배달_등록() {
		return DeliveryFixtures.deliveries()
			.stream()
			.map(request -> step.배달_등록_요청(request))
			.collect(Collectors.toList());
	}

	@Test
	void 배달_조회() {
		int duration = 3;

		List<DeliveryResponse> 배달_목록 = step.배달_목록_조회(duration, getDeliveriesDocument());

		DeliveryAcceptanceAssertions.목록_조회됨(배달_목록, DeliveryFixtures.deliveries());
	}

	@Test
	void 배달_주소_수정() {
		DeliveryResponse response = 등록된_배달_목록.get(0);
		DeliveryRequest request = DeliveryRequest.of("부산시 해운대구", "");

		ExtractableResponse<Response> 수정_응답 = step.목적지_수정(response.getId(), request, editDestinationDocument());

		DeliveryAcceptanceAssertions.목적지_수정됨(수정_응답, request);
	}

	private Filter editDestinationDocument() {
		return document("editDestinationDocument",
			preprocessRequest(prettyPrint()),
			preprocessResponse(prettyPrint()),
			pathParameters(
				parameterWithName("id").description("배달 아이디")
			),
			requestFields(
				fieldWithPath("destination").description("수정할 배달 목적지 주소"),
				fieldWithPath("status").ignored()
			),
			responseFields(
				fieldWithPath("id").description("배달 아이디"),
				fieldWithPath("destination").description("수정된 배달 목적지 주소"),
				fieldWithPath("status").description("배달 상태"),
				fieldWithPath("orderedAt").description("주문 일자"))
		);
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
					fieldWithPath("id").description("배달 아이디"),
					fieldWithPath("destination").description("배달 목적지 주소"),
					fieldWithPath("status").description("배달 상태"),
					fieldWithPath("orderedAt").description("주문 일자"))
		);
	}
}