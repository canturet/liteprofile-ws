package com.liteprofile.ws.controller;

import com.liteprofile.ws.model.CustomLink;
import com.liteprofile.ws.service.CustomLinkService;
import com.liteprofile.ws.utils.payload.dto.CustomLinkSaveDto;
import com.liteprofile.ws.utils.payload.dto.CustomLinkUpdateDto;
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

    @GetMapping("/get-custom-link/{id}")
    public CustomLink getCustomLinkById(@PathVariable Long id) {
        return customLinkService.getCustomLinkById(id);
    }

    @GetMapping("/get-custom-links")
    public List<CustomLink> getCustomLinks() {
        return customLinkService.getCustomLinks();
    }

    @PostMapping("/save-custom-link")
    public CustomLink saveCustomLink(@Valid @RequestBody CustomLinkSaveDto customLinkSaveDto) {
        return customLinkService.saveCustomLink(customLinkSaveDto);
    }

    @PutMapping("/update-custom-link/{id}")
    public CustomLink updateCustomLink(@Valid @PathVariable("id") Long id, @RequestBody CustomLinkUpdateDto customLinkUpdateDto) {
        return customLinkService.updateCustomLink(id, customLinkUpdateDto);
    }

    @DeleteMapping("/delete-custom-link/{id}")
    public ResponseEntity<?> saveCustomLink(@PathVariable("id") Long id) {
        return customLinkService.deleteCustomLink(id);
    }

}
