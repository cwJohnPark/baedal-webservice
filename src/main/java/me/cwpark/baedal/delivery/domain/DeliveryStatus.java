package me.cwpark.baedal.delivery.domain;

public enum DeliveryStatus {
	PREPARING,
	ON_THE_WAY,
	COMPLETED,
	CANCELED;

	public boolean isUpdatableDelivery() {
		return this == PREPARING;
	}
}
