package com.example.springboothelloworld.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author BigBoss
 * @version v1.0
 * @since 2021-08-17
 */
@Component
public class ConfigProperties {

    @Value("${com.minidiva.toast}")
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
