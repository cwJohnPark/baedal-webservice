package me.cwpark.baedal.member.exception;

public class InvalidTypeCountPasswordException extends InvalidPasswordException {

	private static final String MESSAGE = "패스워드는 영어 대문자, 영어 소문자, 숫자, 특수문자 중 3종류 이상이어야 합니다.";

	public InvalidTypeCountPasswordException() {
		super(MESSAGE);
	}
}
