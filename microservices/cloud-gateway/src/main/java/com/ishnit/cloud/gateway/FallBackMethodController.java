package com.ishnit.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("userServiceFallBack")
    public String userServiceFallBackMethod(){
        return " User Service is taking too long to return response back, Please try again later.";
    }

    @GetMapping("departmentServiceFallBack")
    public String departmentServiceFallBackMethod(){
        return " Department Service is taking too long to return response back, Please try again later.";
    }
}
