package com.liteprofile.ws.service;

import com.liteprofile.ws.model.Biography;
import com.liteprofile.ws.utils.payload.dto.BiographyCreateDto;
import com.liteprofile.ws.utils.payload.dto.BiographyUpdateDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface BiographyService {

    Biography getBiographyById(Long id);

    List<Biography> getBiographies();

    Biography createBiography(BiographyCreateDto biographyCreateDto) throws IOException;

    Biography updateBiography(Long id, BiographyUpdateDto biographyUpdateDto) throws IOException;

    ResponseEntity<?> deleteBiography(Long id);

    Biography getBiographyByUserId(Long id);

}
