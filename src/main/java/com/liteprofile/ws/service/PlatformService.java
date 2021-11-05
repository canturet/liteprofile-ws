package com.liteprofile.ws.service;

import com.liteprofile.ws.model.Platform;
import com.liteprofile.ws.utils.payload.dto.PlatformCreateDto;
import com.liteprofile.ws.utils.payload.dto.PlatformUpdateDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface PlatformService {

    List<Platform> getPlatforms();

    Platform getPlatformById(Long id);

    Platform getPlatformByPlatformName(String platformName);

    Platform createPlatform(PlatformCreateDto platformCreateDto) throws IOException;

    public Stream<Platform> getAllFiles();

    Platform updatePlatform(Long id, PlatformUpdateDto platformUpdateDto) throws IOException;

    ResponseEntity<?> deletePlatform(Long id);

}
