package com.ishnit.user.controller;

import com.ishnit.user.entity.User;
import com.ishnit.user.service.UserService;
import com.ishnit.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User saveUser(@RequestBody User user){
        log.info("Inside UserController Save:"+user.toString());
        return userService.saveUser(user);
    }

    @GetMapping("/users/{id}")
    public ResponseTemplateVO getUserIncludingDepartment(@PathVariable("id") Long userId){
        log.info("Inside UserController getUserIncludingDepartment:"+userId);
        return userService.getUserAndDepartment(userId);
    }
}
