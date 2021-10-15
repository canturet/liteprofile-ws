package com.liteprofile.ws.utils.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class CustomLinkUpdateDto {

    @NotBlank(message = "'url' alanı zorunludur.")
    @Size(max = 200, message = "'url' alanı maksimum 200 karakterden oluşabilir.")
    private String url;

    @NotBlank(message = "'title' alanı zorunludur.")
    @Size(max = 50, message = "'title' alanı maksimum 50 karakterden oluşabilir.")
    private String title;

    @Size(max = 200, message = "'description' alanı maksimum 200 karakterden oluşabilir.")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate = LocalDateTime.now();

}
