package me.cwpark.baedal.acceptance;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class AcceptanceTestStep<Q, P> {

	private final RestAssuredClient client;
	private final Class<P> responseClass;

	public AcceptanceTestStep(RestAssuredClient client, Class<P> responseClass) {
		this.client = client;
		this.responseClass = responseClass;
	}

	public ExtractableResponse<Response> 등록_요청(RequestSpecification given, Q requestBody) {
		return client.post(getRequestPath(), requestBody, given);
	}

	public ExtractableResponse<Response> 등록_요청(Q requestBody) {
		return client.post(getRequestPath(), requestBody);
	}

	public List<ExtractableResponse<Response>> 등록_요청(List<Q> requestsBody) {
		return requestsBody.stream()
			.map(this::등록_요청)
			.collect(Collectors.toList());
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

	public P 등록되어_있음(Q requestBody) {
		return 등록됨(등록_요청(requestBody));
	}

	public List<P> 등록되어_있음(List<Q> requestBodyList) {
		return 등록됨(등록_요청(requestBodyList));
	}

	public void 등록_실패함(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isNotEqualTo(HttpStatus.OK.value());
	}

	public List<P> 목록_조회() {
		ExtractableResponse<Response> response = client.get(getRequestPath());
		return response.body()
			.jsonPath()
			.getList(".", responseClass);
	}


	public ExtractableResponse<Response> 수정_요청(String requestPath, long id, Object requestBody) {
		return client.put(requestPath,
			id,
			requestBody);
	}

	public void 수정됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public void 수정_실패함(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isNotEqualTo(HttpStatus.OK.value());
	}

	public ExtractableResponse<Response> 삭제_요청(String requestPath, Long id) {
		return client.delete(requestPath, id);
	}

	public void 삭제됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}

	protected abstract String getRequestPath();

}
