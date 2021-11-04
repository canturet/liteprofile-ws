package com.liteprofile.ws.service;

import com.google.zxing.WriterException;
import com.liteprofile.ws.model.User;
import com.liteprofile.ws.utils.payload.dto.LoginDto;
import com.liteprofile.ws.utils.payload.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;

public interface UserService {

    ResponseEntity<?> login(LoginDto loginDto);

    ResponseEntity<?> register(RegisterDto registerDto);

    ResponseEntity<?> confirmAccount(String confirmationToken) throws IOException, WriterException;

    User getUserById(Long id);

    Optional<User> getUserByUsername(String username);

}
