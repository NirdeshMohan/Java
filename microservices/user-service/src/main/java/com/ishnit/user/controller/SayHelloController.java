package com.ishnit.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {
    @Value("${welcome.message}")
    private String message;

    @GetMapping("/")
    public String sayHello(){
        //return "Hello From SpringBoot!!!!";
        return message;
    }
}
