package me.cwpark.baedal.delivery;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import me.cwpark.baedal.delivery.dto.DeliveryRequest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;

public class DeliveryAcceptanceAssertions {

	public static void 목록_조회됨(List<DeliveryResponse> actual, List<DeliveryRequest> expected) {
		assertThat(actual.size()).isEqualTo(expected.size());
	}
}
