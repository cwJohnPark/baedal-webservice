package me.cwpark.baedal.acceptance;

import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.specification.RequestSpecification;
import me.cwpark.baedal.utils.DatabaseCleanUtils;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

	@Value("${local.server.port}")
	int port;

	@Autowired
	private DatabaseCleanUtils databaseCleanup;

	@Autowired
	private ObjectMapper objectMapper;

	protected RestAssuredClient client;

	protected RequestSpecification given;

	@BeforeEach
	public void setUp(RestDocumentationContextProvider restDocumentation) {
		RestAssured.port = port;
		RestAssured.config = RestAssured.config()
			.objectMapperConfig(
				new ObjectMapperConfig().jackson2ObjectMapperFactory((cls, charset) -> objectMapper));

		given = new RequestSpecBuilder()
			.addFilter(documentationConfiguration(restDocumentation))
			.build();

		client = new RestAssuredClient(given);

		databaseCleanup.cleanUp();
	}
}
