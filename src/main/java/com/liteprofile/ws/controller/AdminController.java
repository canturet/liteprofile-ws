package com.liteprofile.ws.controller;

import com.liteprofile.ws.model.*;
import com.liteprofile.ws.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/get-users")
    public List<User> getUsers() {
        return adminService.getUsers();
    }

    @GetMapping("/get-custom-links")
    public List<CustomLink> getCustomLinks() {
        return adminService.getCustomLinks();
    }

    @GetMapping("/get-social-links")
    public List<SocialLink> getSocialLinks() {
        return adminService.getSocialLinks();
    }

    @GetMapping("/get-texts")
    public List<Text> getTexts() {
        return adminService.getTexts();
    }

    @GetMapping("/get-videos")
    public List<Video> getVideos() {
        return adminService.getVideos();
    }

    @GetMapping("/get-custom-links-by-user-id/{id}")
    public List<CustomLink> getCustomLinksByUserId(@PathVariable("id") Long id) {
        return customLinkService.getCustomLinksByUserId(id);
    }

    @GetMapping("/get-social-links-by-user-id/{id}")
    public List<SocialLink> getSocialLinksByUserId(@PathVariable("id") Long id) {
        return socialLinkService.findSocialLinksByUserId(id);
    }

    @GetMapping("/get-texts-by-user-id/{id}")
    public List<Text> getTextsByUserId(@PathVariable("id") Long id) {
        return textService.getTextsByUserId(id);
    }

    @GetMapping("/get-users-by-id/{id}")
    public User getUsersById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/get-videos-by-user-id/{id}")
    public List<Video> getVideosByUserId(@PathVariable("id") Long id) {
        return videoService.getVideosByUserId(id);
    }
}
