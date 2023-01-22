package me.cwpark.baedal.utils;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class StringUtils {

	private static final Pattern SPECIAL_SYMBOL = Pattern.compile("[^a-zA-Z0-9\\s]");

	public static boolean hasWhiteSpace(String input) {
		return input.chars()
			.anyMatch(Character::isWhitespace);
	}

	public static long getLowerCaseCount(String input) {
		return input.chars()
			.filter(Character::isLowerCase)
			.count();
	}

	public static long getUpperCaseCount(String input) {
		return input.chars()
			.filter(Character::isUpperCase)
			.count();
	}

	public static long getDigitCount(String input) {
		return input.chars()
			.filter(Character::isDigit)
			.count();
	}

	public static long getSpecialSymbolCount(String input) {
		return IntStream.range(0, input.length())
			.mapToObj(index -> input.substring(index, index+1))
			.filter(token -> SPECIAL_SYMBOL.matcher(token).find())
			.count();
	}
}
