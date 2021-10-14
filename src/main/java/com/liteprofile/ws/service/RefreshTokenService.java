package com.liteprofile.ws.service;

import com.liteprofile.ws.model.RefreshToken;
import com.liteprofile.ws.utils.payload.request.TokenRefreshRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

    ResponseEntity<?> refreshToken(TokenRefreshRequest tokenRefreshRequest);

}
