package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.Biography;
import com.liteprofile.ws.repository.BiographyRepository;
import com.liteprofile.ws.service.BiographyService;
import com.liteprofile.ws.service.UserService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.BiographyCreateDto;
import com.liteprofile.ws.utils.payload.dto.BiographyUpdateDto;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BiographyServiceImpl implements BiographyService {

    private static final String BIOGRAPHY_IMAGE_PATH = "./src/main/resources/static/img/biography_images/";

    @Autowired
    BiographyRepository biographyRepository;

    @Autowired
    UserService userService;

    @Autowired
    Message message;

    @Override
    public Biography getBiographyById(Long id) {
        return biographyRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Biography> getBiographies() {
        return biographyRepository.findAll();
    }

    @Override
    public Biography createBiography(BiographyCreateDto biographyCreateDto) throws IOException {
        if (userService.getUserById(biographyCreateDto.getUserId()) != null) {
            Biography biography = new Biography(biographyCreateDto.getUserId(), biographyCreateDto.getName(), biographyCreateDto.getDescription(), biographyCreateDto.getImage().getBytes(), LocalDateTime.now(), LocalDateTime.now());
            Path path = Paths.get(BIOGRAPHY_IMAGE_PATH + biographyCreateDto.getImage().getOriginalFilename());
            Files.write(path, biographyCreateDto.getImage().getBytes());
            return biographyRepository.save(biography);
        }
        return null;
    }

    @Override
    public Biography updateBiography(Long id, BiographyUpdateDto biographyUpdateDto) throws IOException {
        Biography existingBiography = biographyRepository.findById(id).orElseThrow();
        existingBiography.setName(biographyUpdateDto.getName());
        existingBiography.setDescription(biographyUpdateDto.getDescription());
        existingBiography.setUpdatedDate(biographyUpdateDto.getUpdatedDate());
        existingBiography.setData(biographyUpdateDto.getImage().getBytes());
        Path path = Paths.get(BIOGRAPHY_IMAGE_PATH + biographyUpdateDto.getImage().getOriginalFilename());
        Files.write(path, biographyUpdateDto.getImage().getBytes());
        return biographyRepository.save(existingBiography);
    }

    @Override
    public ResponseEntity<?> deleteBiography(Long id) {
        Biography existingBiography = biographyRepository.findById(id).orElseThrow();
        biographyRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(message.getBiographyDeletedSuccessfully()));
    }

    @Transactional
    @Override
    public Biography getBiographyByUserId(Long id) {
        return (Biography) biographyRepository.findByUserId(id);
    }

}
