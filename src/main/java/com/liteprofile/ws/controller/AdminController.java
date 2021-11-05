package com.liteprofile.ws.controller;

import com.liteprofile.ws.model.Platform;
import com.liteprofile.ws.service.*;
import com.liteprofile.ws.utils.payload.dto.PlatformCreateDto;
import com.liteprofile.ws.utils.payload.dto.PlatformUpdateDto;
import com.liteprofile.ws.utils.payload.response.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

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
    PlatformService platformService;

    @Autowired
    BiographyService biographyService;

    private static final String PLATFORM_IMAGE_PATH = "./src/main/resources/static/img/platform_images/";

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/getCustomLinks")
    public ResponseEntity<?> getCustomLinks() {
        return ResponseEntity.ok(customLinkService.getCustomLinks());
    }

    @GetMapping("/getSocialLinks")
    public ResponseEntity<?> getSocialLinks() {
        return ResponseEntity.ok(socialLinkService.getSocialLinks());
    }

    @GetMapping("/getTexts")
    public ResponseEntity<?> getTexts() {
        return ResponseEntity.ok(textService.getTexts());
    }

    @GetMapping("/getBiographies")
    public ResponseEntity<?> getBiographies() {
        return ResponseEntity.ok(biographyService.getBiographies());
    }

    @GetMapping("/getVideos")
    public ResponseEntity<?> getVideos() {
        return ResponseEntity.ok(videoService.getVideos());
    }

    @GetMapping("/getPlatforms")
    public ResponseEntity<?> getPlatforms() {
        return ResponseEntity.ok(platformService.getPlatforms());
    }

    @GetMapping("/getCustomLinksByUserId/{id}")
    public ResponseEntity<?> getCustomLinksByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customLinkService.getCustomLinksByUserId(id));
    }

    @GetMapping("/getSocialLinksByUserId/{id}")
    public ResponseEntity<?> getSocialLinksByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(socialLinkService.findSocialLinksByUserId(id));
    }

    @GetMapping("/getTextsByUserId/{id}")
    public ResponseEntity<?> getTextsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(textService.getTextsByUserId(id));
    }

    @GetMapping("/getUsersById/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/getVideosByUserId/{id}")
    public ResponseEntity<?> getVideosByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(videoService.getVideosByUserId(id));
    }

    @GetMapping("/getPlatformById/{id}")
    public ResponseEntity<?> getPlatformById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(platformService.getPlatformById(id));
    }

    @GetMapping("/getBiographyByUserId/{id}")
    public ResponseEntity<?> getBiographyByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(biographyService.getBiographyByUserId(id));
    }

    @PostMapping("/createPlatform")
    public ResponseEntity<?> createPlatform(@ModelAttribute PlatformCreateDto platformCreateDto) throws IOException {
        return ResponseEntity.ok(platformService.createPlatform(platformCreateDto));
    }

    @PutMapping("/updatePlatform/{id}")
    public ResponseEntity<?> updatePlatform(@Valid @PathVariable("id") Long id, @ModelAttribute PlatformUpdateDto platformUpdateDto) throws IOException {
        return ResponseEntity.ok(platformService.updatePlatform(id, platformUpdateDto));
    }

    @DeleteMapping("/deletePlatform/{id}")
    public ResponseEntity<?> deletePlatform(@PathVariable("id") Long id) {
        return ResponseEntity.ok(platformService.deletePlatform(id));
    }

}
