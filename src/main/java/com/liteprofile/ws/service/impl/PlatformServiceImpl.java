package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.CustomLink;
import com.liteprofile.ws.model.Platform;
import com.liteprofile.ws.repository.PlatformRepository;
import com.liteprofile.ws.service.PlatformService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.PlatformCreateDto;
import com.liteprofile.ws.utils.payload.dto.PlatformUpdateDto;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PlatformServiceImpl implements PlatformService {

    private static final String PLATFORM_IMAGE_PATH = "./src/main/resources/static/img/platform_images/";

    @Autowired
    PlatformRepository platformRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Message message;

    @Override
    public List<Platform> getPlatforms() {
        return platformRepository.findAll();
    }

    @Override
    public Platform getPlatformById(Long id) {
        return platformRepository.findById(id).orElseThrow();
    }

    @Override
    public Platform getPlatformByPlatformName(String platformName) {
        return platformRepository.findByPlatformName(platformName);
    }

    @Override
    public Platform createPlatform(PlatformCreateDto platformCreateDto) throws IOException {
        Platform platform = modelMapper.map(platformCreateDto, Platform.class);
        String fileName = StringUtils.cleanPath(platformCreateDto.getImage().getOriginalFilename());
        Platform FileDB = new Platform(platformCreateDto.getPlatformName(),platformCreateDto.getDescription(), fileName, platformCreateDto.getImage().getContentType(), platformCreateDto.getImage().getBytes(), LocalDateTime.now(), LocalDateTime.now());
        Path path = Paths.get(PLATFORM_IMAGE_PATH + platformCreateDto.getImage().getOriginalFilename());
        Files.write(path, platformCreateDto.getImage().getBytes());
        return platformRepository.save(FileDB);
    }

    public Stream<Platform> getAllFiles() {
        return platformRepository.findAll().stream();
    }

    @Override
    public Platform updatePlatform(Long id, PlatformUpdateDto platformUpdateDto) throws IOException {
        Platform existingPlatform = platformRepository.findById(id).orElseThrow();
        existingPlatform.setPlatformName(platformUpdateDto.getPlatformName());
        existingPlatform.setDescription(platformUpdateDto.getDescription());
        existingPlatform.setFileName(StringUtils.cleanPath(platformUpdateDto.getImage().getOriginalFilename()));
        existingPlatform.setPlatformType(platformUpdateDto.getImage().getContentType());
        existingPlatform.setData(platformUpdateDto.getImage().getBytes());
        existingPlatform.setUpdatedDate(platformUpdateDto.getUpdatedDate());
        Path path = Paths.get(PLATFORM_IMAGE_PATH + platformUpdateDto.getImage().getOriginalFilename());
        Files.write(path, platformUpdateDto.getImage().getBytes());
        return platformRepository.save(existingPlatform);
    }

    @Override
    public ResponseEntity<?> deletePlatform(Long id) {
        Platform existingPlatform = platformRepository.findById(id).orElseThrow();
        platformRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(message.getPlatformDeletedSuccessfully()));
    }

}
