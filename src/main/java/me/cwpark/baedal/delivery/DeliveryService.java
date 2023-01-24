package me.cwpark.baedal.delivery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.delivery.domain.Delivery;
import me.cwpark.baedal.delivery.dto.DeliveryRequest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;
import me.cwpark.baedal.member.Member;
import me.cwpark.baedal.member.MemberService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;
	private final MemberService memberService;

	public List<DeliveryResponse> findAllByMemberEmail(String email, int duration) {
		return DeliveryResponse.of(
			deliveryRepository.findAllByMemberEmail(email, getOrderedAfter(duration)));
	}

	private LocalDateTime getOrderedAfter(int durationDays) {
		return LocalDate.now()
			.minusDays(durationDays)
			.atStartOfDay();
	}

	@Transactional
	public DeliveryResponse save(String email, DeliveryRequest deliveryRequest) {
		Member member = memberService.findByEmail(email);
		Delivery delivery = deliveryRequest.toEntity(member);
		return DeliveryResponse.of(deliveryRepository.save(delivery));
	}
}
