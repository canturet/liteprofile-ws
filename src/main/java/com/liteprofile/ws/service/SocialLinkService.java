package com.liteprofile.ws.service;

import com.liteprofile.ws.model.SocialLink;
import com.liteprofile.ws.utils.payload.dto.SocialLinkCreateDto;
import com.liteprofile.ws.utils.payload.dto.SocialLinkUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SocialLinkService {

    SocialLink getSocialLinkById(Long id);

    List<SocialLink> getSocialLinks();

    SocialLink createSocialLink(SocialLinkCreateDto socialLinkCreateDto);

    SocialLink updateSocialLink(Long id, SocialLinkUpdateDto socialLinkUpdateDto);

    ResponseEntity<?> deleteSocialLink(Long id);

    List<SocialLink> findSocialLinksByUserId(Long id);

}
