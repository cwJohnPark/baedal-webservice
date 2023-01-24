package me.cwpark.baedal.delivery;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import me.cwpark.baedal.delivery.dto.DeliveryRequest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;

public class DeliveryAcceptanceAssertions {

	public static void 목록_조회됨(List<DeliveryResponse> actual, List<DeliveryRequest> expected) {
		assertThat(actual.size()).isEqualTo(expected.size());
	}

	public static void 목적지_수정됨(ExtractableResponse<Response> actual, DeliveryRequest expected) {
		assertThat(actual.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(actual.as(DeliveryResponse.class))
			.extracting(DeliveryResponse::getDestination)
			.isEqualTo(expected.getDestination());

	}
}
