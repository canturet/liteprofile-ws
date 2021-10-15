package com.liteprofile.ws.utils.payload.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {

    @NotBlank(message = "'username' alanı zorunludur.")
    @Size(max = 20, message = "'username' alanı maksimum 20 karakterden oluşabilir.")
    private String username;

    @NotBlank(message = "'password' alanı zorunludur.")
    @Size(max = 20, message = "'password' alanı maksimum 20 karakterden oluşabilir.")
    private String password;

}
