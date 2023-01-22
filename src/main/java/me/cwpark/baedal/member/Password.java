package me.cwpark.baedal.member;

import static me.cwpark.baedal.utils.StringUtils.*;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import me.cwpark.baedal.member.exception.IncludeWhitespacePasswordException;
import me.cwpark.baedal.member.exception.InvalidLengthPasswordException;
import me.cwpark.baedal.member.exception.InvalidTypeCountPasswordException;
import me.cwpark.baedal.utils.StringUtils;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

	private static final int MIN_NUMBER_OF_TYPE = 3;
	private static final int MIN_LENGTH = 12;

	private String password;

	public static Password of(String password) {
		validatePassword(password);

		return new Password(password);
	}

	private static void validatePassword(String password) {
		if (hasWhiteSpace(password)) {
			throw new IncludeWhitespacePasswordException();
		}
		if (isLengthInvalid(password)) {
			throw new InvalidLengthPasswordException();
		}
		if (isTypeCountInvalid(password)) {
			throw new InvalidTypeCountPasswordException();
		}
	}

	private static boolean isLengthInvalid(String password) {
		return password.length() < MIN_LENGTH;
	}

	private static boolean isTypeCountInvalid(String password) {
		boolean hasLowercase = StringUtils.getLowerCaseCount(password) > 0;
		boolean hasUppercase = StringUtils.getUpperCaseCount(password) > 0;
		boolean hasSpecialSymbol = StringUtils.getSpecialSymbolCount(password) > 0;
		boolean hasNumber = StringUtils.getDigitCount(password) > 0;

		int count = (hasLowercase ? 1 : 0)
			+ (hasUppercase ? 1 : 0)
			+ (hasSpecialSymbol ? 1 : 0)
			+ (hasNumber ? 1 : 0);
		return count < MIN_NUMBER_OF_TYPE;
	}
}
