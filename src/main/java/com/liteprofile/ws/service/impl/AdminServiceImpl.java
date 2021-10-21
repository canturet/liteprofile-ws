package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.*;
import com.liteprofile.ws.repository.*;
import com.liteprofile.ws.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

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
    public List<CustomLink> getCustomLinks() {
        return customLinkRepository.findAll();
    }

    @Override
    public List<SocialLink> getSocialLinks() {
        return socialLinkRepository.findAll();
    }

    @Override
    public List<Text> getTexts() {
        return textRepository.findAll();
    }

    @Override
    public List<Video> getVideos() {
        return videoRepository.findAll();
    }

    @Override
    public List<Biography> getBiographies() {
        return biographyRepository.findAll();
    }

}
