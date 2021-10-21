package com.liteprofile.ws.controller;

import com.liteprofile.ws.model.*;
import com.liteprofile.ws.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    CustomLinkService customLinkService;

    @Autowired
    SocialLinkService socialLinkService;

    @Autowired
    TextService textService;

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;

    @Autowired
    BiographyService biographyService;

    @GetMapping("/get-users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(adminService.getUsers());
    }

    @GetMapping("/get-custom-links")
    public ResponseEntity<?> getCustomLinks() {
        return ResponseEntity.ok(adminService.getCustomLinks());
    }

    @GetMapping("/get-social-links")
    public ResponseEntity<?> getSocialLinks() {
        return ResponseEntity.ok(adminService.getSocialLinks());
    }

    @GetMapping("/get-texts")
    public ResponseEntity<?> getTexts() {
        return ResponseEntity.ok(adminService.getTexts());
    }

    @GetMapping("/get-biographies")
    public ResponseEntity<?> getBiographies() {
        return ResponseEntity.ok(adminService.getBiographies());
    }

    @GetMapping("/get-videos")
    public ResponseEntity<?> getVideos() {
        return ResponseEntity.ok(adminService.getVideos());
    }

    @GetMapping("/get-custom-links-by-user-id/{id}")
    public ResponseEntity<?> getCustomLinksByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customLinkService.getCustomLinksByUserId(id));
    }

    @GetMapping("/get-social-links-by-user-id/{id}")
    public ResponseEntity<?> getSocialLinksByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(socialLinkService.findSocialLinksByUserId(id));
    }

    @GetMapping("/get-texts-by-user-id/{id}")
    public ResponseEntity<?> getTextsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(textService.getTextsByUserId(id));
    }

    @GetMapping("/get-users-by-id/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/get-videos-by-user-id/{id}")
    public ResponseEntity<?> getVideosByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(videoService.getVideosByUserId(id));
    }

    @GetMapping("/get-biographies-by-user-id/{id}")
    public ResponseEntity<?> getBiographiesByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(biographyService.getBiographiesByUserId(id));
    }

}
