package com.liteprofile.ws.controller;

import com.liteprofile.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserProfileByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getUserProfileByUsername(username));
    }

}
