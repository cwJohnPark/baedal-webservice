package me.cwpark.baedal.delivery;

import java.util.List;

import org.assertj.core.util.Lists;

import me.cwpark.baedal.delivery.domain.DeliveryStatus;
import me.cwpark.baedal.delivery.dto.DeliveryRequest;

public class DeliveryFixtures {

	public static List<DeliveryRequest> deliveries() {
		return Lists.newArrayList(
			DeliveryRequest.of("서울시 강서구", DeliveryStatus.PREPARING.name()),
			DeliveryRequest.of("서울시 강서구", DeliveryStatus.ON_THE_WAY.name()),
			DeliveryRequest.of("서울시 강서구", DeliveryStatus.COMPLETED.name())
		);
	}
}
