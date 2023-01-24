package me.cwpark.baedal.delivery.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.cwpark.baedal.delivery.domain.Delivery;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryResponse {

	private String destination;
	private String status;
	private LocalDateTime orderedAt;

	public static List<DeliveryResponse> of(List<Delivery> deliveries) {
		return deliveries.stream()
			.map(DeliveryResponse::of)
			.collect(Collectors.toList());
	}

	public static DeliveryResponse of(Delivery delivery) {
		return new DeliveryResponse(
			delivery.getDestination(),
			delivery.getStatus().name(),
			delivery.getCreatedAt());
	}
}
