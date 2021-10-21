package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.Biography;
import com.liteprofile.ws.repository.BiographyRepository;
import com.liteprofile.ws.service.BiographyService;
import com.liteprofile.ws.service.UserService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.BiographyCreateDto;
import com.liteprofile.ws.utils.payload.dto.BiographyUpdateDto;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiographyServiceImpl implements BiographyService {

    @Autowired
    BiographyRepository biographyRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

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
    public Biography createBiography(BiographyCreateDto biographyCreateDto) {
        if (userService.getUserById(biographyCreateDto.getUserId()) != null) {
            Biography biography = modelMapper.map(biographyCreateDto, Biography.class);
            return biographyRepository.save(biography);
        }
        return null;
    }

    @Override
    public Biography updateBiography(Long id, BiographyUpdateDto biographyUpdateDto) {
        Biography existingBiography = biographyRepository.findById(id).orElseThrow();
        existingBiography.setName(biographyUpdateDto.getName());
        existingBiography.setDescription(biographyUpdateDto.getDescription());
        existingBiography.setUpdatedDate(biographyUpdateDto.getUpdatedDate());
        return biographyRepository.save(existingBiography);
    }

    @Override
    public ResponseEntity<?> deleteBiography(Long id) {
        Biography existingBiography = biographyRepository.findById(id).orElseThrow();
        biographyRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(message.getBiographyDeletedSuccessfully()));
    }

    @Override
    public List<Biography> getBiographiesByUserId(Long id) {
        return biographyRepository.findByUserId(id);
    }
}
