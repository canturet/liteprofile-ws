package com.liteprofile.ws.service.impl;

import com.liteprofile.ws.model.Text;
import com.liteprofile.ws.repository.TextRepository;
import com.liteprofile.ws.service.TextService;
import com.liteprofile.ws.service.UserService;
import com.liteprofile.ws.utils.message.Message;
import com.liteprofile.ws.utils.payload.dto.TextCreateDto;
import com.liteprofile.ws.utils.payload.dto.TextUpdateDto;
import com.liteprofile.ws.utils.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextServiceImpl implements TextService {

    @Autowired
    TextRepository textRepository;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    Message message;

    @Override
    public Text getTextById(Long id) {
        return textRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Text> getTexts() {
        return textRepository.findAll();
    }

    @Override
    public Text createText(TextCreateDto textCreateDto) {
        if (userService.getUserById(textCreateDto.getUserId()) != null) {
            Text text = modelMapper.map(textCreateDto, Text.class);
            return textRepository.save(text);
        }
        return null;
    }

    @Override
    public Text updateText(Long id, TextUpdateDto textUpdateDto) {
        Text existingText = textRepository.findById(id).orElseThrow();
        existingText.setMessage(textUpdateDto.getMessage());
        existingText.setUpdatedDate(textUpdateDto.getUpdatedDate());
        return textRepository.save(existingText);
    }

    @Override
    public ResponseEntity<?> deleteText(Long id) {
        Text existingText = textRepository.findById(id).orElseThrow();
        textRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse(message.getTextDeletedSuccessfully()));
    }
}
