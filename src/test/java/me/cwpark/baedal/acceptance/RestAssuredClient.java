package me.cwpark.baedal.acceptance;

import java.util.Map;
import java.util.Objects;

import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {

	RequestSpecification spec;

	public RestAssuredClient(RequestSpecification spec) {
		this.spec = spec;
	}

	public void setDocument(Filter document) {
		this.spec.filter(document);
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

	public ExtractableResponse<Response> get(String path, Map<String, String> params, Filter document) {
		return RestAssured
			.given(spec).filter(document).log().all()
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.when().log().all()
			.params(params)
			.get(path).then().log().all()
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

	public void setAccessToken(String accessToken) {
		if (Objects.isNull(accessToken) || accessToken.isBlank()) {
			return;
		}

		spec.given().auth().oauth2(accessToken);
	}
}
