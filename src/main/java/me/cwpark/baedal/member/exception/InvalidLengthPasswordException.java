package me.cwpark.baedal.member.exception;

public class InvalidLengthPasswordException extends InvalidPasswordException {

	public InvalidLengthPasswordException() {
		super("패스워드 길이는 12자리 미만일 수 없습니다.");
	}
}
