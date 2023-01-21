package me.cwpark.baedal.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberResponse {
	private String email;
	private String name;

	public static MemberResponse of(String email, String name) {
		return new MemberResponse(email, name);
	}
}
