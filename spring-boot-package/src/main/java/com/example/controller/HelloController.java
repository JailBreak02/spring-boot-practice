package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /***
     * @see *试热部署成功
     * 直接修改 message 的值, Spring Boot 会自动 restartedMain[热加载]
     * @return
     */
    @RequestMapping("/hello")
    public String index() {
        String message = "Big Boss + Ayanami  +++";
        return message;
    }
}
