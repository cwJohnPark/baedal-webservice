package me.cwpark.baedal.infra.security.exception;

public class InvalidAuthenticationException extends RuntimeException {
	private static final String MESSAGE = "인증에 실패하였습니다.";

	public InvalidAuthenticationException() {
		super(MESSAGE);
	}
}
