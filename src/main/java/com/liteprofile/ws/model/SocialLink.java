package com.liteprofile.ws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "social_link")
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "'userId' alanı zorunludur.")
    private Long userId;

    @NotBlank(message = "'url' alanı zorunludur.")
    @Size(max = 200, message = "'url' alanı maksimum 200 karakterden oluşabilir.")
    private String url;

    @NotBlank(message = "'platform' alanı zorunludur.")
    @Size(max = 50, message = "'platform' alanı maksimum 50 karakterden oluşabilir.")
    private String platform;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;

}