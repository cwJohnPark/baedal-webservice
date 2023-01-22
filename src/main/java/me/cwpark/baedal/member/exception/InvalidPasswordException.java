package me.cwpark.baedal.member.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import me.cwpark.baedal.exception.BadRequestException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public abstract class InvalidPasswordException extends BadRequestException {

	public InvalidPasswordException(String message) {
		super(message);
	}
}
