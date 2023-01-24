package me.cwpark.baedal.delivery.exception;

import me.cwpark.baedal.exception.BadRequestException;

public class CannotUpdateDestination extends BadRequestException {

	public static final String MESSAGE = "배달 주소지 변경 불가능한 주문입니다.";

	public CannotUpdateDestination() {
		super(MESSAGE);
	}
}
