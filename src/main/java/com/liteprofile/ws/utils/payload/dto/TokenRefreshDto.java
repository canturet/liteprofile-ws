package com.liteprofile.ws.utils.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TokenRefreshDto {

    @NotBlank(message = "'refreshToken' alanı zorunludur.")
    private String refreshToken;

}
