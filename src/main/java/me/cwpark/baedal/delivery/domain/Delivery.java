package me.cwpark.baedal.delivery.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.cwpark.baedal.BaseEntity;
import me.cwpark.baedal.delivery.exception.CannotUpdateDestination;
import me.cwpark.baedal.member.Member;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private String destination;

	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	public Delivery(Member member, String destination, DeliveryStatus status) {
		this.member = member;
		this.destination = destination;
		this.status = status;
	}

	public boolean isOwnedBy(Member other) {
		return this.member.equals(other);
	}

	public void updateDestination(String destination) {
		if (!status.isUpdatableDelivery()) {
			throw new CannotUpdateDestination();
		}
		this.destination = destination;
	}
}
