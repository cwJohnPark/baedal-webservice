package me.cwpark.baedal.member;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import me.cwpark.baedal.member.exception.IncludeWhitespacePasswordException;
import me.cwpark.baedal.member.exception.InvalidLengthPasswordException;
import me.cwpark.baedal.member.exception.InvalidTypeCountPasswordException;

class PasswordTest {

	@ParameterizedTest
	@ValueSource(strings = {"123456AAAaaa", "ABCDEFGHIJ12a", "__________A1"})
	void 유효한_패스워드(String input) {
		assertThatCode(() -> Password.of(input))
			.doesNotThrowAnyException();
	}

	@ParameterizedTest
	@ValueSource(strings = {"123456AAA aaa", " ABCDEFGHIJ12a", "__________A1 "})
	void 무효한_패스워드_공백포함(String input) {
		assertThatThrownBy(() -> Password.of(input))
			.isInstanceOf(IncludeWhitespacePasswordException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"123456789AAA", "123456789???", "12!@#$%^&*()"})
	void 무효한_패스워드_3종류_미만(String input) {
		assertThatThrownBy(() -> Password.of(input))
			.isInstanceOf(InvalidTypeCountPasswordException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", "1", "123456789Ab", "!@#$%^&*()1"})
	void 무효한_패스워드_길이가_12자리_미만(String input) {
		assertThatThrownBy(() -> Password.of(input))
			.isInstanceOf(InvalidLengthPasswordException.class);
	}
}
