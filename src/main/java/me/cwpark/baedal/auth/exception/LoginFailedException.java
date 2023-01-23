package me.cwpark.baedal.auth.exception;

import me.cwpark.baedal.exception.BadRequestException;

public class LoginFailedException extends BadRequestException {

	private static final String MESSAGE = "로그인 정보가 옯바르지 않습니다.";

	public LoginFailedException() {
		super(MESSAGE);
	}
}
