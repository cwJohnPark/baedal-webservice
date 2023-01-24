package me.cwpark.baedal.delivery;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import io.restassured.filter.Filter;

public class DeliveryApiDocuments {

	static Filter failedEditDestinationDocument() {
		return document("failedEditDestinationDocument",
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
				fieldWithPath("message").description("응답 메시지"),
				fieldWithPath("type").description("오류 타입"))
		);
	}

	static Filter editDestinationDocument() {
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

	static Filter getDeliveriesDocument() {
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
