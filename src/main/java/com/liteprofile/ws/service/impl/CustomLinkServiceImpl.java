package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.CustomLink;
import com.liteprofile.ws.repository.CustomLinkRepository;
import com.liteprofile.ws.service.CustomLinkService;
import com.liteprofile.ws.service.UserService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.CustomLinkCreateDto;
import com.liteprofile.ws.utils.payload.dto.CustomLinkUpdateDto;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomLinkServiceImpl implements CustomLinkService {

    @Autowired
    CustomLinkRepository customLinkRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Message message;

    @Override
    public CustomLink getCustomLinkById(Long id) {
        return customLinkRepository.findById(id).orElseThrow();
    }

    @Override
    public List<CustomLink> getCustomLinks() {
        return customLinkRepository.findAll();
    }

    @Override
    public CustomLink createCustomLink(CustomLinkCreateDto customLinkCreateDto) {
        if (userService.getUserById(customLinkCreateDto.getUserId()) != null) {
            CustomLink customLink = modelMapper.map(customLinkCreateDto, CustomLink.class);
            return customLinkRepository.save(customLink);
        }
        return null;
    }

    @Override
    public CustomLink updateCustomLink(Long id, CustomLinkUpdateDto customLinkUpdateDto) {
        CustomLink existingCustomLink = customLinkRepository.findById(id).orElseThrow();
        existingCustomLink.setUrl(customLinkUpdateDto.getUrl());
        existingCustomLink.setTitle(customLinkUpdateDto.getTitle());
        existingCustomLink.setDescription(customLinkUpdateDto.getDescription());
        existingCustomLink.setUpdatedDate(customLinkUpdateDto.getUpdatedDate());
        return customLinkRepository.save(existingCustomLink);
    }

    @Override
    public ResponseEntity<?> deleteCustomLink(Long id) {
        CustomLink existingCustomLink = customLinkRepository.findById(id).orElseThrow();
        customLinkRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(message.getCustomLinkDeletedSuccessfully()));
    }

}
