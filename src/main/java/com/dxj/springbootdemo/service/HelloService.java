package com.dxj.springbootdemo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String hello() {
        return "hello!";
    }
}
