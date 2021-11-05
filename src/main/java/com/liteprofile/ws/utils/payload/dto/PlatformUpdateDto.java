package com.liteprofile.ws.utils.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class PlatformUpdateDto {

    @NotNull(message = "'image' alanı zorunludur")
    private MultipartFile image;

    @NotNull(message = "'platformName' alanı zorunludur.")
    private String platformName;

    @Size(max = 200, message = "'description' alanı maksimum 200 karakterden oluşabilir.")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate = LocalDateTime.now();
}
