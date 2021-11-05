package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.Video;
import com.liteprofile.ws.repository.VideoRepository;
import com.liteprofile.ws.service.UserService;
import com.liteprofile.ws.service.VideoService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.VideoCreateDto;
import com.liteprofile.ws.utils.payload.dto.VideoUpdateDto;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoRepository videoRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Message message;

    @Override
    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElseThrow(null);
    }

    @Override
    public List<Video> getVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Video createVideo(VideoCreateDto videoCreateDto) {
        if (userService.getUserById(videoCreateDto.getUserId()) != null) {
            Video video = modelMapper.map(videoCreateDto, Video.class);
            return videoRepository.save(video);
        }
        return null;
    }

    @Override
    public Video updateVideo(Long id, VideoUpdateDto videoUpdateDto) {
        Video existingVideo = videoRepository.findById(id).orElseThrow();
        existingVideo.setUrl(videoUpdateDto.getUrl());
        existingVideo.setUpdatedDate(videoUpdateDto.getUpdatedDate());
        return videoRepository.save(existingVideo);
    }

    @Override
    public ResponseEntity<?> deleteVideo(Long id) {
        Video existingVideo = videoRepository.findById(id).orElseThrow();
        videoRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(message.getVideDeletedSuccessfully()));
    }

    @Override
    public List<Video> getVideosByUserId(Long id) {
        return videoRepository.findByUserId(id);
    }

}
