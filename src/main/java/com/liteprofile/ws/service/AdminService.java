package com.liteprofile.ws.service;

import com.liteprofile.ws.model.*;

import java.util.List;

public interface AdminService {
    List<User> getUsers();

    List<CustomLink> getCustomLinks();

    List<SocialLink> getSocialLinks();

    List<Text> getTexts();

    List<Video> getVideos();

}
