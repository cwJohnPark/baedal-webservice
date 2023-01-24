package me.cwpark.baedal.member;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.member.dto.MemberRequest;
import me.cwpark.baedal.member.dto.MemberResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/members")
	public ResponseEntity<MemberResponse> register(@RequestBody MemberRequest request) {
		Member savedMember = memberService.save(request);
		return ResponseEntity.created(
				getLocation(savedMember))
			.body(MemberResponse.of(
				savedMember.getEmail(),
				savedMember.getName()));
	}

	private URI getLocation(Member savedMember) {
		return URI.create("/members/" + savedMember.getId());
	}
}
