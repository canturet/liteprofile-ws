package com.liteprofile.ws.service;

import com.liteprofile.ws.model.Text;
import com.liteprofile.ws.utils.payload.dto.TextCreateDto;
import com.liteprofile.ws.utils.payload.dto.TextUpdateDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TextService {

    Text getTextById(Long id);

    List<Text> getTexts();

    Text createText(TextCreateDto textCreateDto);

    Text updateText(Long id, TextUpdateDto textUpdateDto);

    ResponseEntity<?> deleteText(Long id);

}
