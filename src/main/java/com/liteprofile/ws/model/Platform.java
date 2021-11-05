package com.liteprofile.ws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "'platformName' alanını giriniz.")
    private String platformName;

    @Size(max = 200, message = "'description' alanı maksimum 200 karakterden oluşabilir.")
    private String description;

    @Lob
    private byte[] data;

    private String platformType;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;

    private String fileName;

    public Platform(String platformName,String description, String fileName, String platformType, byte[] data, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.platformName = platformName;
        this.description = description;
        this.fileName = fileName;
        this.platformType = platformType;
        this.data = data;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}