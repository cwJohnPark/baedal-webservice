package me.cwpark.baedal.delivery;

import java.util.List;

import org.apache.groovy.util.Maps;

import io.restassured.filter.Filter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import me.cwpark.baedal.acceptance.AcceptanceTestStep;
import me.cwpark.baedal.acceptance.RestAssuredClient;
import me.cwpark.baedal.delivery.dto.DeliveryRequest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;

public class DeliveryAcceptanceStep extends AcceptanceTestStep<DeliveryRequest, DeliveryResponse> {

	public static final String REQUEST_PATH = "/deliveries";
	public static final String DURATION = "duration";

	public DeliveryAcceptanceStep(RestAssuredClient client, String token) {
		super(client, DeliveryResponse.class, token);
	}

	@Override
	protected String getRequestPath() {
		return REQUEST_PATH;
	}

	public List<DeliveryResponse> 배달_목록_조회(int duration, Filter deliveriesDocument) {
		return 목록_조회(
			Maps.of(DURATION, duration + ""),
			deliveriesDocument);
	}

	public DeliveryResponse 배달_등록_요청(DeliveryRequest request) {
		return 등록_요청(request).as(DeliveryResponse.class);
	}

	public ExtractableResponse<Response> 목적지_수정(Long id, DeliveryRequest request, Filter document) {
		return 수정_요청(id, request, document);
	}
}
