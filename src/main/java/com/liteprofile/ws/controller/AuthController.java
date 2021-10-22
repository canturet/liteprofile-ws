package com.liteprofile.ws.controller;

import com.liteprofile.ws.utils.payload.dto.BiographyUpdateDto;
import com.liteprofile.ws.utils.payload.dto.LoginDto;
import com.liteprofile.ws.utils.payload.dto.TokenRefreshDto;
import com.liteprofile.ws.utils.payload.dto.RegisterDto;
import com.liteprofile.ws.service.RefreshTokenService;
import com.liteprofile.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        return userService.register(registerDto);
    }

    @GetMapping("/confirm-account")
    public ResponseEntity<?> confirmAccount(@Valid @RequestParam("token") String token) {
        return userService.confirmAccount(token);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshDto tokenRefreshDto) {
        return refreshTokenService.refreshToken(tokenRefreshDto);
    }

}
