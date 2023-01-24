package me.cwpark.baedal.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.cwpark.baedal.auth.dto.TokenRequest;
import me.cwpark.baedal.auth.dto.TokenResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@RequestBody TokenRequest tokenRequest) {
		TokenResponse tokenResponse = authService.login(tokenRequest);
		return ResponseEntity.ok(tokenResponse);
	}
}
