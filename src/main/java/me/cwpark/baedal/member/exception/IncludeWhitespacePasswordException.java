package me.cwpark.baedal.member.exception;

public class IncludeWhitespacePasswordException extends InvalidPasswordException {

	public IncludeWhitespacePasswordException() {
		super("패스워드에 공백을 포함할 수 없습니다.");
	}
}
