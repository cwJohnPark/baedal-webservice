package me.cwpark.baedal.acceptance;

import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import me.cwpark.baedal.utils.DatabaseCleanUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

	@Value("${local.server.port}")
	int port;

	@Autowired
	private DatabaseCleanUtils databaseCleanup;

	@Autowired
	private ObjectMapper objectMapper;

	protected RestAssuredClient client;

	@BeforeEach
	public void setUp(RestDocumentationContextProvider restDocumentation) {
		RestAssured.port = port;
		RestAssured.config = RestAssured.config()
			.objectMapperConfig(
				new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper));

		client = new RestAssuredClient(new RequestSpecBuilder()
			.addFilter(documentationConfiguration(restDocumentation))
			.build());

		databaseCleanup.cleanUp();
	}
}
