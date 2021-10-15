package com.liteprofile.ws.controller;

import com.liteprofile.ws.model.CustomLink;
import com.liteprofile.ws.model.SocialLink;
import com.liteprofile.ws.service.CustomLinkService;
import com.liteprofile.ws.service.SocialLinkService;
import com.liteprofile.ws.utils.payload.dto.CustomLinkCreateDto;
import com.liteprofile.ws.utils.payload.dto.CustomLinkUpdateDto;
import com.liteprofile.ws.utils.payload.dto.SocialLinkCreateDto;
import com.liteprofile.ws.utils.payload.dto.SocialLinkUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    CustomLinkService customLinkService;

    @Autowired
    SocialLinkService socialLinkService;

    @GetMapping("/get-custom-link/{id}")
    public CustomLink getCustomLinkById(@PathVariable Long id) {
        return customLinkService.getCustomLinkById(id);
    }

    @GetMapping("/get-custom-links")
    public List<CustomLink> getCustomLinks() {
        return customLinkService.getCustomLinks();
    }

    @PostMapping("/create-custom-link")
    public CustomLink createCustomLink(@Valid @RequestBody CustomLinkCreateDto customLinkCreateDto) {
        return customLinkService.createCustomLink(customLinkCreateDto);
    }

    @PutMapping("/update-custom-link/{id}")
    public CustomLink updateCustomLink(@Valid @PathVariable("id") Long id, @RequestBody CustomLinkUpdateDto customLinkUpdateDto) {
        return customLinkService.updateCustomLink(id, customLinkUpdateDto);
    }

    @DeleteMapping("/delete-custom-link/{id}")
    public ResponseEntity<?> deleteCustomLink(@PathVariable("id") Long id) {
        return customLinkService.deleteCustomLink(id);
    }

    @GetMapping("/get-social-link/{id}")
    public SocialLink getSocialLinkById(@PathVariable Long id) {
        return socialLinkService.getSocialLinkById(id);
    }

    @GetMapping("/get-social-links")
    public List<SocialLink> getSocialLinks() {
        return socialLinkService.getSocialLinks();
    }

    @PostMapping("/create-social-link")
    public SocialLink createSocialLink(@Valid @RequestBody SocialLinkCreateDto socialLinkCreateDto) {
        return socialLinkService.createSocialLink(socialLinkCreateDto);
    }

    @PutMapping("/update-social-link/{id}")
    public SocialLink updateSocialLink(@Valid @PathVariable("id") Long id, @RequestBody SocialLinkUpdateDto socialLinkUpdateDto) {
        return socialLinkService.updateSocialLink(id, socialLinkUpdateDto);
    }

    @DeleteMapping("/delete-social-link/{id}")
    public ResponseEntity<?> deleteSocialLink(@PathVariable("id") Long id) {
        return socialLinkService.deleteSocialLink(id);
    }

}
