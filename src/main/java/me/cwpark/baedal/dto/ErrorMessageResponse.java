package me.cwpark.baedal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessageResponse {

	private String message;
	private String type;

	public static ErrorMessageResponse of(Exception exception) {
		return new ErrorMessageResponse(
			exception.getMessage(),
			exception.getClass().getSimpleName()
		);
	}
}
