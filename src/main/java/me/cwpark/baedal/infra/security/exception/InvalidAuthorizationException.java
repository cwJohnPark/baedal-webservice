package me.cwpark.baedal.infra.security.exception;

public class InvalidAuthorizationException extends RuntimeException {
	private static final String MESSAGE = "권한이 없습니다.";

	public InvalidAuthorizationException() {
		super(MESSAGE);
	}
}
