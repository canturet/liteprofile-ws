package com.liteprofile.ws.utils.payload.response;

import com.liteprofile.ws.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserProfileResponse {

    Biography biography;
    List<CustomLink> customLinks;
    List<SocialLink> socialLinks;
    List<Text> texts;
    List<Video> videos;

}
