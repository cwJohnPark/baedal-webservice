package me.cwpark.baedal.delivery.exception;

import static java.lang.String.*;

import me.cwpark.baedal.exception.BadRequestException;

public class DeliveryNotFoundException extends BadRequestException {

	private static final String MESSAGE = "배달 정보('%s')를 찾을 수 없습니다.";

	public DeliveryNotFoundException(Long id) {
		super(format(MESSAGE, id));
	}
}
