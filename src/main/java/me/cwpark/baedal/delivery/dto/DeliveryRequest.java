package me.cwpark.baedal.delivery.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.cwpark.baedal.delivery.domain.Delivery;
import me.cwpark.baedal.delivery.domain.DeliveryStatus;
import me.cwpark.baedal.member.Member;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryRequest {

	private String destination;
	private String status;

	public static DeliveryRequest of(String destination, String status) {
		return new DeliveryRequest(destination, status);
	}

	public Delivery toEntity(Member member) {
		return new Delivery(member, destination, DeliveryStatus.valueOf(status.toUpperCase()));
	}
}
