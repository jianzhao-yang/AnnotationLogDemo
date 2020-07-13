package com.example.demo.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("hello")
    public String he() {
        User user = new User();
        user.setAge(20);
        user.setName("yang");
        return helloService.hello(user);
    }

}
