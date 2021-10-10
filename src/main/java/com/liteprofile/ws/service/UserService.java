package com.liteprofile.ws.service;

import com.liteprofile.ws.payload.request.LoginRequest;
import com.liteprofile.ws.payload.request.TokenRefreshRequest;
import com.liteprofile.ws.payload.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> login(LoginRequest loginRequest);

    ResponseEntity<?> register(RegisterRequest registerRequest);

    ResponseEntity<?> refreshToken(TokenRefreshRequest tokenRefreshRequest);

}
