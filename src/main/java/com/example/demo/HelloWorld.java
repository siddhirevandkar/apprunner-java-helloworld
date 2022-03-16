package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/")
    public String index() {
        String s = "Hello World from AWS App Runner. The JDK version is " + System.getProperty("java.version");
        return s;
    }

}

