package me.cwpark.baedal.infra.security;

public enum Roles {
	ADMIN, MEMBER;

	public static final String PREFIX_ROLE = "ROLE_";

	public String toValue() {
		return PREFIX_ROLE + name();
	}
}
