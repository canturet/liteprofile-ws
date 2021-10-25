package com.liteprofile.ws.utils.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class PlatformCreateDto {

    @NotNull(message = "'image' alanı zorunludur")
    private MultipartFile image;

    @NotNull(message = "'platformName' alanı zorunludur.")
    private String platformName;


}
