package me.cwpark.baedal.utils;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringUtilsTest {

	@ParameterizedTest
	@CsvSource(value = {"aAA,1", "aAaAaAaAa,5", "AAAAA,0"})
	void testGetLowercaseCount(String stringInput, int expectedCount) {
		assertThat(StringUtils.getLowerCaseCount(stringInput))
			.isEqualTo(expectedCount);
	}

	@ParameterizedTest
	@ValueSource(strings = {" a ", " a b c", "AA AAA"})
	void testHasWhiteSpace(String stringInput) {
		assertThat(StringUtils.hasWhiteSpace(stringInput))
			.isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"aAA,2", "aAaAaAaAa,4", "AAAAA,5"})
	void testGetUppercaseCount(String stringInput, int expectedCount) {
		assertThat(StringUtils.getUpperCaseCount(stringInput))
			.isEqualTo(expectedCount);
	}

	@ParameterizedTest
	@CsvSource(value = {"AAA,0", "ABCDabcd,0", "A1A,1", "12345,5"})
	void testGetDigitCount(String stringInput, int expectedCount) {
		assertThat(StringUtils.getDigitCount(stringInput))
			.isEqualTo(expectedCount);
	}

	@ParameterizedTest
	@CsvSource(value = {"AAA,0", "_abc,1", "abc_,1", "12 345,0"})
	void testGetSpecialSymbolCount(String stringInput, int expectedCount) {
		assertThat(StringUtils.getSpecialSymbolCount(stringInput))
			.isEqualTo(expectedCount);
	}

}
