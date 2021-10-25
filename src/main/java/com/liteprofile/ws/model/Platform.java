package com.liteprofile.ws.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@Entity
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "'platformName' alanını giriniz.")
    private String platformName;

    @Lob
    private byte[] data;

    private String platformType;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedDate;

    private String fileName;

    public Platform(String platformName, String fileName, String platformType, byte[] data, LocalDateTime createdDate) {
        this.platformName = platformName;
        this.fileName = fileName;
        this.platformType = platformType;
        this.data = data;
        this.createdDate = createdDate;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;

    }
}