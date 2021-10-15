package com.liteprofile.ws.service;

import com.liteprofile.ws.model.CustomLink;
import com.liteprofile.ws.utils.payload.dto.CustomLinkCreateDto;
import com.liteprofile.ws.utils.payload.dto.CustomLinkUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomLinkService {

    CustomLink getCustomLinkById(Long id);

    List<CustomLink> getCustomLinks();

    CustomLink createCustomLink(CustomLinkCreateDto customLinkCreateDto);

    CustomLink updateCustomLink(Long id, CustomLinkUpdateDto customLinkUpdateDto);

    ResponseEntity<?> deleteCustomLink(Long id);

}
