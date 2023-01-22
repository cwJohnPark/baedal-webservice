package me.cwpark.baedal.member.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.cwpark.baedal.member.Member;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRequest {
	private String email;
	private String name;
	private String password;

	public static MemberRequest of(String email, String name, String password) {
		return new MemberRequest(email, name, password);
	}

	public Member toEntity() {
		return Member.create(email, name, password);
	}
}
