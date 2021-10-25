package com.liteprofile.ws.controller;

import com.liteprofile.ws.model.*;
import com.liteprofile.ws.service.*;
import com.liteprofile.ws.utils.payload.dto.*;
import com.liteprofile.ws.utils.payload.response.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    @GetMapping("/get-custom-link/{id}")
    public ResponseEntity<?> getCustomLinkById(@PathVariable Long id) {
        return ResponseEntity.ok(customLinkService.getCustomLinkById(id));
    }

    @GetMapping("/get-custom-links")
    public ResponseEntity<?> getCustomLinks() {
        return ResponseEntity.ok(customLinkService.getCustomLinks());
    }

    @PostMapping("/create-custom-link")
    public ResponseEntity<?> createCustomLink(@Valid @RequestBody CustomLinkCreateDto customLinkCreateDto) {
        return ResponseEntity.ok(customLinkService.createCustomLink(customLinkCreateDto));
    }

    @PutMapping("/update-custom-link/{id}")
    public ResponseEntity<?> updateCustomLink(@Valid @PathVariable("id") Long id, @RequestBody CustomLinkUpdateDto customLinkUpdateDto) {
        return ResponseEntity.ok(customLinkService.updateCustomLink(id, customLinkUpdateDto));
    }

    @DeleteMapping("/delete-custom-link/{id}")
    public ResponseEntity<?> deleteCustomLink(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customLinkService.deleteCustomLink(id));
    }

    @GetMapping("/get-social-link/{id}")
    public ResponseEntity<?> getSocialLinkById(@PathVariable Long id) {
        return ResponseEntity.ok(socialLinkService.getSocialLinkById(id));
    }

    @GetMapping("/get-social-links")
    public ResponseEntity<?> getSocialLinks() {
        return ResponseEntity.ok(socialLinkService.getSocialLinks());
    }

    @PostMapping("/create-social-link")
    public ResponseEntity<?> createSocialLink(@Valid @RequestBody SocialLinkCreateDto socialLinkCreateDto) {
        return ResponseEntity.ok(socialLinkService.createSocialLink(socialLinkCreateDto));
    }

    @PutMapping("/update-social-link/{id}")
    public ResponseEntity<?> updateSocialLink(@Valid @PathVariable("id") Long id, @RequestBody SocialLinkUpdateDto socialLinkUpdateDto) {
        return ResponseEntity.ok(socialLinkService.updateSocialLink(id, socialLinkUpdateDto));
    }

    @DeleteMapping("/delete-social-link/{id}")
    public ResponseEntity<?> deleteSocialLink(@PathVariable("id") Long id) {
        return ResponseEntity.ok(socialLinkService.deleteSocialLink(id));
    }

    @GetMapping("/get-text/{id}")
    public ResponseEntity<?> getTextById(@PathVariable Long id) {
        return ResponseEntity.ok(textService.getTextById(id));
    }

    @GetMapping("/get-texts")
    public ResponseEntity<?> getTexts() {
        return ResponseEntity.ok(textService.getTexts());
    }

    @PostMapping("/create-text")
    public ResponseEntity<?> createText(@Valid @RequestBody TextCreateDto textCreateDto) {
        return ResponseEntity.ok(textService.createText(textCreateDto));
    }

    @PutMapping("/update-text/{id}")
    public ResponseEntity<?> updateText(@Valid @PathVariable("id") Long id, @RequestBody TextUpdateDto textUpdateDto) {
        return ResponseEntity.ok(textService.updateText(id, textUpdateDto));
    }

    @DeleteMapping("/delete-text/{id}")
    public ResponseEntity<?> deleteText(@PathVariable("id") Long id) {
        return ResponseEntity.ok(textService.deleteText(id));
    }

    @GetMapping("/get-video/{id}")
    public ResponseEntity<?> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }

    @GetMapping("/get-videos")
    public ResponseEntity<?> getVideos() {
        return ResponseEntity.ok(videoService.getVideos());
    }

    @PostMapping("/create-video")
    public ResponseEntity<?> createVideo(@Valid @RequestBody VideoCreateDto videoCreateDto) {
        return ResponseEntity.ok(videoService.createVideo(videoCreateDto));
    }

    @PutMapping("/update-video/{id}")
    public ResponseEntity<?> updateVideo(@Valid @PathVariable("id") Long id, @RequestBody VideoUpdateDto videoUpdateDto) {
        return ResponseEntity.ok(videoService.updateVideo(id, videoUpdateDto));
    }

    @DeleteMapping("/delete-video/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(videoService.deleteVideo(id));
    }

    @GetMapping("/get-biography/{id}")
    public ResponseEntity<?> getBiographyById(@PathVariable Long id) {
        return ResponseEntity.ok(biographyService.getBiographyById(id));
    }

    @GetMapping("/get-biographies")
    public ResponseEntity<?> getBiographies() {
        return ResponseEntity.ok(biographyService.getBiographies());
    }

    @PostMapping("/create-biography")
    public ResponseEntity<?> createBiography(@Valid @RequestBody BiographyCreateDto biographyCreateDto) {
        return ResponseEntity.ok(biographyService.createBiography(biographyCreateDto));
    }

    @PutMapping("/update-biography/{id}")
    public ResponseEntity<?> updateBiography(@Valid @PathVariable("id") Long id, @RequestBody BiographyUpdateDto biographyUpdateDto) {
        return ResponseEntity.ok(biographyService.updateBiography(id, biographyUpdateDto));
    }

    @DeleteMapping("/delete-biography/{id}")
    public ResponseEntity<?> deleteBiography(@PathVariable("id") Long id) {
        return ResponseEntity.ok(biographyService.deleteBiography(id));
    }

    @PostMapping("/create-platform")
    public ResponseEntity<?> createPlatform(@ModelAttribute PlatformCreateDto platformCreateDto) throws IOException {
        return ResponseEntity.ok(platformService.createPlatform(platformCreateDto));
    }
    @PutMapping("/update-platform/{id}")
    public ResponseEntity<?> updatePlatform(@Valid @PathVariable("id") Long id,@ModelAttribute PlatformUpdateDto platformUpdateDto) throws IOException {
        return ResponseEntity.ok(platformService.updatePlatform(id,platformUpdateDto));
    }
    @DeleteMapping("/delete-platform/{id}")
    public ResponseEntity<?> deletePlatform(@PathVariable("id") Long id){
        return ResponseEntity.ok(platformService.deletePlatform(id));
    }
    @GetMapping("/get-platforms")
    public ResponseEntity<List<ResponseFile>> getPlatforms() {
        List<ResponseFile> files = platformService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getPlatformName(),
                    fileDownloadUri,
                    dbFile.getPlatformType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/get-platform/{id}")
    public ResponseEntity<byte[]> getPlatformById(@PathVariable Long id) {
        Platform fileDB = platformService.getPlatformById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getPlatformName() + "\"")
                .body(fileDB.getData());
    }
}
