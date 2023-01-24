package me.cwpark.baedal.member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.exception.MemberNotFoundException;
import me.cwpark.baedal.member.dto.MemberRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	public Member save(MemberRequest request) {
		return memberRepository.save(request.toEntity());
	}

	public Member findByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new MemberNotFoundException(email));
	}
}
