package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.RefreshToken;
import com.liteprofile.ws.repository.TokenRefreshRepository;
import com.liteprofile.ws.repository.UserRepository;
import com.liteprofile.ws.service.RefreshTokenService;
import com.liteprofile.ws.utils.exception.TokenRefreshException;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.TokenRefreshDto;
import com.liteprofile.ws.utils.payload.response.RefreshTokenResponse;
import com.liteprofile.ws.utils.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${liteprofile.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private TokenRefreshRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    Message message;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), message.getExpiredRefreshToken());
        }
        return token;
    }

    @Override
    public ResponseEntity<?> refreshToken(TokenRefreshDto tokenRefreshDto) {
        String requestRefreshToken = tokenRefreshDto.getRefreshToken();
        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new RefreshTokenResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        message.getRefreshTokenNotFound()));
    }

}
