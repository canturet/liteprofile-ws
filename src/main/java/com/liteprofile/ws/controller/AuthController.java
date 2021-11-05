package com.liteprofile.ws.controller;

import com.google.zxing.WriterException;
import com.liteprofile.ws.service.AuthService;
import com.liteprofile.ws.utils.payload.dto.LoginDto;
import com.liteprofile.ws.utils.payload.dto.TokenRefreshDto;
import com.liteprofile.ws.utils.payload.dto.RegisterDto;
import com.liteprofile.ws.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @GetMapping("/confirmAccount")
    public ResponseEntity<?> confirmAccount(@Valid @RequestParam("token") String token) throws IOException, WriterException {
        return authService.confirmAccount(token);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshDto tokenRefreshDto) {
        return refreshTokenService.refreshToken(tokenRefreshDto);
    }

}
