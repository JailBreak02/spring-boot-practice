package com.example.springboothelloworld.controller;

import com.example.springboothelloworld.Model.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    ConfigProperties configProperties;

    @RequestMapping("/hello")
    public String index() {

        if (configProperties != null) {
            String msg = configProperties.getMessage();
            return msg;
        } else {
            return "Hello World";
        }
    }
}
