package me.cwpark.baedal.delivery;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.auth.AuthenticatedMember;
import me.cwpark.baedal.auth.LoginMember;
import me.cwpark.baedal.delivery.dto.DeliveryRequest;
import me.cwpark.baedal.delivery.dto.DeliveryResponse;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

	private final DeliveryService deliveryService;

	@PostMapping("/deliveries")
	public ResponseEntity<DeliveryResponse> addDelivery(@AuthenticatedMember LoginMember member,
		@RequestBody DeliveryRequest deliveryRequest) {
		DeliveryResponse savedDelivery = deliveryService.save(member.getEmail(), deliveryRequest);
		return ResponseEntity.ok(savedDelivery);
	}

	@GetMapping("/deliveries")
	public ResponseEntity<List<DeliveryResponse>> getDeliveries(@AuthenticatedMember LoginMember member,
		@RequestParam Integer duration) {
		return ResponseEntity
			.ok(deliveryService
				.findAllByMemberEmail(member.getEmail(), duration));
	}

	@PutMapping("/deliveries/{id}")
	public ResponseEntity<DeliveryResponse> addDelivery(@AuthenticatedMember LoginMember member,
		@PathVariable Long id,
		@RequestBody DeliveryRequest deliveryRequest) {
		DeliveryResponse savedDelivery = deliveryService.update(member.getEmail(), id, deliveryRequest);
		return ResponseEntity.ok(savedDelivery);
	}

}
