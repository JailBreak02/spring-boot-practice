package com.example.springbootweb.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NeoProperties {

    @Value("${com.example.springbootweb.util.NeoProperties.title}")
    private String title;
    @Value("${com.example.springbootweb.util.NeoProperties.description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
