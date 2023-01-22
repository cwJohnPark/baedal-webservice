package me.cwpark.baedal.exception;

import static java.lang.String.*;

public class MemberNotFoundException extends BadRequestException {
	private static final String MESSAGE = "사용자 %s 을 찾을 수 없습니다.";

	public MemberNotFoundException(String email) {
		super(format(MESSAGE, email));
	}
}
