package com.liteprofile.ws.utils.message;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "message")
@Configuration("messageProperties")
public class Message {
    private String usernameIsAlreadyTaken;
    private String emailIsAlreadyInUse;
    private String roleNotFound;
    private String userRegisteredSuccessfully;
    private String customLinkDeletedSuccessfully;
}
