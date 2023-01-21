package me.cwpark.baedal.member;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.cwpark.baedal.member.dto.MemberRequest;
import me.cwpark.baedal.member.dto.MemberResponse;

@RestController
public class MemberController {

	@PostMapping("/members")
	public ResponseEntity<MemberResponse> register(@RequestBody MemberRequest request) {
		return ResponseEntity.created(URI.create("/members/" + request.getEmail()))
			.body(MemberResponse.of(request.getEmail(), request.getName()));
	}
}
