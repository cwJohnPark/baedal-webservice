package me.cwpark.baedal.auth.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenRequest {
    private String email;
    private String password;

    public static TokenRequest of(String email, String password) {
        return new TokenRequest(email, password);
    }
}
