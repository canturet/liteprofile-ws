package com.liteprofile.ws.service;

import com.liteprofile.ws.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User getUserById(Long id);

    ResponseEntity<?> getUserProfileByUsername(String username);

}
