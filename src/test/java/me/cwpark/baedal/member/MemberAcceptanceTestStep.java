package me.cwpark.baedal.member;

import me.cwpark.baedal.acceptance.AcceptanceTestStep;
import me.cwpark.baedal.acceptance.RestAssuredClient;
import me.cwpark.baedal.member.dto.MemberRequest;
import me.cwpark.baedal.member.dto.MemberResponse;

public class MemberAcceptanceTestStep extends AcceptanceTestStep<MemberRequest, MemberResponse> {

	public static final String REQUEST_PATH = "/members";

	public MemberAcceptanceTestStep(RestAssuredClient client) {
		super(client, MemberResponse.class);
	}

	@Override
	protected String getRequestPath() {
		return REQUEST_PATH;
	}

}
