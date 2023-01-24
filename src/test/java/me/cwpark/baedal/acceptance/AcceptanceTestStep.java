package me.cwpark.baedal.acceptance;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import io.restassured.filter.Filter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class AcceptanceTestStep<Q, P> {

	private final RestAssuredClient client;
	private final Class<P> responseClass;

	public AcceptanceTestStep(RestAssuredClient client, Class<P> responseClass, String accessToken) {
		this.client = client;
		this.responseClass = responseClass;
		client.setAccessToken(accessToken);
	}

	public AcceptanceTestStep(RestAssuredClient client, Class<P> responseClass) {
		this(client, responseClass, "");
	}

	public ExtractableResponse<Response> 등록_요청(RequestSpecification given, Q requestBody) {
		return client.post(getRequestPath(), requestBody, given);
	}

	public ExtractableResponse<Response> 등록_요청(Q requestBody) {
		return client.post(getRequestPath(), requestBody);
	}

	public P 등록됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
		return response.body()
			.as(responseClass);
	}

	public List<P> 등록됨(List<ExtractableResponse<Response>> responses) {
		return responses.stream()
			.map(this::등록됨)
			.collect(Collectors.toList());
	}

	public List<P> 목록_조회(Map<String, String> params, Filter document) {
		ExtractableResponse<Response> response = client.get(getRequestPath(), params, document);
		return response.body()
			.jsonPath()
			.getList(".", responseClass);
	}

	public ExtractableResponse<Response> 수정_요청(long id, Object requestBody, Filter document) {
		client.setDocument(document);
		return client.put(
			getRequestPathWithParameter(),
			id,
			requestBody);
	}

	private String getRequestPathWithParameter() {
		return getRequestPath() + "/{id}";
	}

	protected abstract String getRequestPath();
}
