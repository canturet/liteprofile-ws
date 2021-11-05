package com.liteprofile.ws.controller;

import com.liteprofile.ws.model.*;
import com.liteprofile.ws.service.*;
import com.liteprofile.ws.utils.payload.dto.*;
import com.liteprofile.ws.utils.payload.response.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    CustomLinkService customLinkService;

    @Autowired
    SocialLinkService socialLinkService;

    @Autowired
    TextService textService;

    @Autowired
    VideoService videoService;

    @Autowired
    BiographyService biographyService;

    @Autowired
    PlatformService platformService;

    @GetMapping("/getCustomLinkById/{id}")
    public ResponseEntity<?> getCustomLinkById(@PathVariable Long id) {
        return ResponseEntity.ok(customLinkService.getCustomLinkById(id));
    }

    @GetMapping("/getCustomLinks")
    public ResponseEntity<?> getCustomLinks() {
        return ResponseEntity.ok(customLinkService.getCustomLinks());
    }

    @PostMapping("/createCustomLink")
    public ResponseEntity<?> createCustomLink(@Valid @RequestBody CustomLinkCreateDto customLinkCreateDto) {
        return ResponseEntity.ok(customLinkService.createCustomLink(customLinkCreateDto));
    }

    @PutMapping("/updateCustomLink/{id}")
    public ResponseEntity<?> updateCustomLink(@Valid @PathVariable("id") Long id, @RequestBody CustomLinkUpdateDto customLinkUpdateDto) {
        return ResponseEntity.ok(customLinkService.updateCustomLink(id, customLinkUpdateDto));
    }

    @DeleteMapping("/deleteCustomLink/{id}")
    public ResponseEntity<?> deleteCustomLink(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customLinkService.deleteCustomLink(id));
    }

    @GetMapping("/getSocialLinkById/{id}")
    public ResponseEntity<?> getSocialLinkById(@PathVariable Long id) {
        return ResponseEntity.ok(socialLinkService.getSocialLinkById(id));
    }

    @GetMapping("/getSocialLinks")
    public ResponseEntity<?> getSocialLinks() {
        return ResponseEntity.ok(socialLinkService.getSocialLinks());
    }

    @PostMapping("/createSocialLink")
    public ResponseEntity<?> createSocialLink(@Valid @RequestBody SocialLinkCreateDto socialLinkCreateDto) {
        return ResponseEntity.ok(socialLinkService.createSocialLink(socialLinkCreateDto));
    }

    @PutMapping("/updateSocialLink/{id}")
    public ResponseEntity<?> updateSocialLink(@Valid @PathVariable("id") Long id, @RequestBody SocialLinkUpdateDto socialLinkUpdateDto) {
        return ResponseEntity.ok(socialLinkService.updateSocialLink(id, socialLinkUpdateDto));
    }

    @DeleteMapping("/deleteSocialLink/{id}")
    public ResponseEntity<?> deleteSocialLink(@PathVariable("id") Long id) {
        return ResponseEntity.ok(socialLinkService.deleteSocialLink(id));
    }

    @GetMapping("/getTextById/{id}")
    public ResponseEntity<?> getTextById(@PathVariable Long id) {
        return ResponseEntity.ok(textService.getTextById(id));
    }

    @GetMapping("/getTexts")
    public ResponseEntity<?> getTexts() {
        return ResponseEntity.ok(textService.getTexts());
    }

    @PostMapping("/createText")
    public ResponseEntity<?> createText(@Valid @RequestBody TextCreateDto textCreateDto) {
        return ResponseEntity.ok(textService.createText(textCreateDto));
    }

    @PutMapping("/updateText/{id}")
    public ResponseEntity<?> updateText(@Valid @PathVariable("id") Long id, @RequestBody TextUpdateDto textUpdateDto) {
        return ResponseEntity.ok(textService.updateText(id, textUpdateDto));
    }

    @DeleteMapping("/deleteText/{id}")
    public ResponseEntity<?> deleteText(@PathVariable("id") Long id) {
        return ResponseEntity.ok(textService.deleteText(id));
    }

    @GetMapping("/getVideoById/{id}")
    public ResponseEntity<?> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @GetMapping("/getVideos")
    public ResponseEntity<?> getVideos() {
        return ResponseEntity.ok(videoService.getVideos());
    }

    @PostMapping("/createVideo")
    public ResponseEntity<?> createVideo(@Valid @RequestBody VideoCreateDto videoCreateDto) {
        return ResponseEntity.ok(videoService.createVideo(videoCreateDto));
    }

    @PutMapping("/updateVideo/{id}")
    public ResponseEntity<?> updateVideo(@Valid @PathVariable("id") Long id, @RequestBody VideoUpdateDto videoUpdateDto) {
        return ResponseEntity.ok(videoService.updateVideo(id, videoUpdateDto));
    }

    @DeleteMapping("/deleteVideo/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(videoService.deleteVideo(id));
    }

    @GetMapping("/getBiographyById/{id}")
    public ResponseEntity<?> getBiographyById(@PathVariable Long id) {
        return ResponseEntity.ok(biographyService.getBiographyById(id));
    }

    @GetMapping("/getBiographies")
    public ResponseEntity<?> getBiographies() {
        return ResponseEntity.ok(biographyService.getBiographies());
    }

    @PostMapping("/createBiography")
    public ResponseEntity<?> createBiography(@ModelAttribute BiographyCreateDto biographyCreateDto) throws IOException {
        return ResponseEntity.ok(biographyService.createBiography(biographyCreateDto));
    }

    @PutMapping("/updateBiography/{id}")
    public ResponseEntity<?> updateBiography(@Valid @PathVariable("id") Long id, @ModelAttribute BiographyUpdateDto biographyUpdateDto) throws IOException {
        return ResponseEntity.ok(biographyService.updateBiography(id, biographyUpdateDto));
    }

    @DeleteMapping("/deleteBiography/{id}")
    public ResponseEntity<?> deleteBiography(@PathVariable("id") Long id) {
        return ResponseEntity.ok(biographyService.deleteBiography(id));
    }

}
