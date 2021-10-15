package com.liteprofile.ws.utils.payload.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class RegisterDto {

    @NotBlank(message = "'username' alanı zorunludur.")
    @Size(max = 20, message = "'username' alanı maksimum 20 karakterden oluşabilir.")
    private String username;

    @NotBlank(message = "'email' alanı zorunludur.")
    @Size(max = 50, message = "'email' alanı maksimum 50 karakterden oluşabilir.")
    @Email
    private String email;

    private Set<String> role;

    @NotBlank(message = "'password' alanı zorunludur.")
    @Size(max = 20, message = "'password' alanı maksimum 20 karakterden oluşabilir.")
    private String password;

}
