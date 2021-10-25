package com.liteprofile.ws.utils.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liteprofile.ws.model.Platform;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class SocialLinkUpdateDto {

    @NotBlank(message = "'url' alanı zorunludur.")
    @Size(max = 200, message = "'url' alanı maksimum 200 karakterden oluşabilir.")
    private String url;
    
    @NotNull(message = "'platformName' alanı zorunludur.")
    private String platformName;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate = LocalDateTime.now();

}
