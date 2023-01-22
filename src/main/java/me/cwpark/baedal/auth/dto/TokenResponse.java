package me.cwpark.baedal.auth.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponse {
    private String accessToken;

    public static TokenResponse of(String token) {
        return new TokenResponse(token);
    }

}
