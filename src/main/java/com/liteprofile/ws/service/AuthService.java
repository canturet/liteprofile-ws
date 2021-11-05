package com.liteprofile.ws.service;

import com.google.zxing.WriterException;
import com.liteprofile.ws.utils.payload.dto.LoginDto;
import com.liteprofile.ws.utils.payload.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {

    ResponseEntity<?> login(LoginDto loginDto);

    ResponseEntity<?> register(RegisterDto registerDto);

    ResponseEntity<?> confirmAccount(String confirmationToken) throws IOException, WriterException;

}
