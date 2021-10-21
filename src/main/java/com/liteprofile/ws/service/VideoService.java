package com.liteprofile.ws.service;

import com.liteprofile.ws.model.Video;
import com.liteprofile.ws.utils.payload.dto.VideoCreateDto;
import com.liteprofile.ws.utils.payload.dto.VideoUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VideoService {

    Video getVideoById(Long id);

    List<Video> getVideos();

    Video createVideo(VideoCreateDto videoCreateDto);

    Video updateVideo(Long id, VideoUpdateDto videoUpdateDto);

    ResponseEntity<?> deleteVideo(Long id);

    List<Video> getVideosByUserId(Long id);

}
