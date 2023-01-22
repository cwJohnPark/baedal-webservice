package me.cwpark.baedal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.cwpark.baedal.dto.ErrorMessageResponse;
import me.cwpark.baedal.exception.BadRequestException;

@RestControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorMessageResponse> badRequest(Exception exception) {
		return ResponseEntity.badRequest()
			.body(ErrorMessageResponse.of(exception));
	}
}
