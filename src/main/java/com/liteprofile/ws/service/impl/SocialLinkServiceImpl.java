package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.Platform;
import com.liteprofile.ws.model.SocialLink;
import com.liteprofile.ws.repository.SocialLinkRepository;
import com.liteprofile.ws.service.PlatformService;
import com.liteprofile.ws.service.SocialLinkService;
import com.liteprofile.ws.service.UserService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.SocialLinkCreateDto;
import com.liteprofile.ws.utils.payload.dto.SocialLinkUpdateDto;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialLinkServiceImpl implements SocialLinkService {

    @Autowired
    SocialLinkRepository socialLinkRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Message message;

    @Autowired
    PlatformService platformService;

    @Override
    public SocialLink getSocialLinkById(Long id) {
        return socialLinkRepository.findById(id).orElseThrow();
    }

    @Override
    public List<SocialLink> getSocialLinks() {
        return socialLinkRepository.findAll();
    }

    @Override
    public SocialLink createSocialLink(SocialLinkCreateDto socialLinkCreateDto) {
        if (userService.getUserById(socialLinkCreateDto.getUserId()) != null) {
            Platform platform = platformService.getPlatformByPlatformName(socialLinkCreateDto.getPlatformName());
            SocialLink socialLink = modelMapper.map(socialLinkCreateDto, SocialLink.class);
            socialLink.setPlatformId(platform.getId());
            return socialLinkRepository.save(socialLink);
        }
        return null;
    }

    @Override
    public SocialLink updateSocialLink(Long id, SocialLinkUpdateDto socialLinkUpdateDto) {
        Platform platform = platformService.getPlatformByPlatformName(socialLinkUpdateDto.getPlatformName());
        SocialLink existingSocialLink = socialLinkRepository.findById(id).orElseThrow();
        existingSocialLink.setUrl(socialLinkUpdateDto.getUrl());
        existingSocialLink.setPlatformId(platform.getId());
        existingSocialLink.setUpdatedDate(socialLinkUpdateDto.getUpdatedDate());
        return socialLinkRepository.save(existingSocialLink);
    }

    @Override
    public ResponseEntity<?> deleteSocialLink(Long id) {
        SocialLink existingSocialLink = socialLinkRepository.findById(id).orElseThrow();
        socialLinkRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(message.getSocialLinkDeletedSuccessfully()));
    }

    @Override
    public List<SocialLink> findSocialLinksByUserId(Long id) {
        return socialLinkRepository.findByUserId(id);
    }

}
