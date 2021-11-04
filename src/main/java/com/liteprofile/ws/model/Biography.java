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
@Table(name = "biography")
public class Biography {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "'userId' alanı zorunludur.")
    private Long userId;

    @NotBlank(message = "'name' alanı zorunludur.")
    @Size(max = 20, message = "'name' alanı maksimum 200 karakterden oluşabilir.")
    private String name;

    @Size(max = 200, message = "'description' alanı maksimum 200 karakterden oluşabilir.")
    private String description;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;

    @Lob
    private byte[] data;

    public Biography(Long userId, String name, String description, byte[] data, LocalDateTime createdDate) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.data = data;
        this.createdDate = createdDate;
    }
}
