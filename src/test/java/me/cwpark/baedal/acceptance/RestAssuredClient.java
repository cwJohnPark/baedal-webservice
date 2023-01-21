package me.cwpark.baedal.acceptance;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {

	final RequestSpecification spec;

	public RestAssuredClient(RequestSpecification spec) {
		this.spec = spec;
	}

	public <T> ExtractableResponse<Response> post(String path, T requestBody) {
		return RestAssured.given(spec).log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(requestBody).when().log().all()
			.post(path).then().log().all()
			.extract();
	}
	
	public <T> ExtractableResponse<Response> post(String path, T requestBody,
		RequestSpecification given) {
		return RestAssured.given(given).log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(requestBody).when().log().all()
			.post(path).then().log().all()
			.extract();
	}

	public ExtractableResponse<Response> get(String path) {
		return RestAssured.given(spec).log().all()
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().log().all()
			.get(path).then().log().all()
			.extract();
	}

	public <T> ExtractableResponse<Response> put(String path, Long id, T requestBody) {
		return RestAssured.given(spec).log().all()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(requestBody).when().log().all()
			.put(path, id).then().log().all()
			.extract();
	}

	public ExtractableResponse<Response> delete(String requestPath, Long id) {
		return RestAssured.given(spec).log().all()
			.when().log().all()
			.delete(requestPath, id)
			.then().log().all()
			.extract();
	}
}
