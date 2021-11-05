package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.*;
import com.liteprofile.ws.repository.*;
import com.liteprofile.ws.utils.payload.response.UserProfileResponse;
import com.liteprofile.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomLinkRepository customLinkRepository;

    @Autowired
    SocialLinkRepository socialLinkRepository;

    @Autowired
    TextRepository textRepository;

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    BiographyRepository biographyRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(null);
    }

    @Override
    public ResponseEntity<?> getUserProfileByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Biography biography = biographyRepository.findByUserId(user.get().getId());
        List<CustomLink> customLinks = customLinkRepository.findByUserId(user.get().getId());
        List<SocialLink> socialLinks = socialLinkRepository.findByUserId(user.get().getId());
        List<Text> texts = textRepository.findByUserId(user.get().getId());
        List<Video> videos = videoRepository.findByUserId(user.get().getId());
        return ResponseEntity.ok(new UserProfileResponse(biography, customLinks, socialLinks, texts, videos));
    }

}


