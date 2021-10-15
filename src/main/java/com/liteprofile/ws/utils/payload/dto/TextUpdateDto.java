package com.liteprofile.ws.utils.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class TextUpdateDto {

    @NotBlank(message = "'message' alanı zorunludur.")
    @Size(max = 200, message = "'message' alanı maksimum 200 karakterden oluşabilir.")
    private String message;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate = LocalDateTime.now();

}
