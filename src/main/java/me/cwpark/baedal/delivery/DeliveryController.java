package me.cwpark.baedal.delivery;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.delivery.dto.DeliveryRequest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;
import me.cwpark.baedal.infra.security.LoginMember;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

	private final DeliveryService deliveryService;

	@PostMapping("/deliveries")
	public ResponseEntity<DeliveryResponse> addDelivery(@AuthenticationPrincipal LoginMember member,
		@RequestBody DeliveryRequest deliveryRequest) {
		DeliveryResponse savedDelivery = deliveryService.save(member.getEmail(), deliveryRequest);
		return ResponseEntity.ok(savedDelivery);
	}

	@GetMapping("/deliveries")
	public ResponseEntity<List<DeliveryResponse>> getDeliveries(@AuthenticationPrincipal LoginMember member,
		@RequestParam Integer duration) {
		return ResponseEntity
			.ok(deliveryService
				.findAllByMemberEmail(member.getEmail(), duration));
	}
}