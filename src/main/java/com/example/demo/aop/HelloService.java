package com.example.demo.aop;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    @LogMethod(inputKey = "#user")
    public String hello(User user){
        return user.getName() + "hello";
    }
}
