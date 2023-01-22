package me.cwpark.baedal.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.cwpark.baedal.member.Member;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {
	private String email;
	private String name;

	public static MemberResponse of(String email, String name) {
		return new MemberResponse(email, name);
	}

	public static MemberResponse of(Member member) {
		return of(member.getEmail(), member.getName());
	}
}
