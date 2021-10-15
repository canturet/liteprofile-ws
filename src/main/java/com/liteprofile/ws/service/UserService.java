package com.liteprofile.ws.service;

import com.liteprofile.ws.model.User;
import com.liteprofile.ws.utils.payload.dto.LoginDto;
import com.liteprofile.ws.utils.payload.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> login(LoginDto loginDto);

    ResponseEntity<?> register(RegisterDto registerDto);

    User getUserById(Long id);

}
