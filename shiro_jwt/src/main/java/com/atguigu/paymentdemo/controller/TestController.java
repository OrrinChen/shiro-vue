package com.atguigu.paymentdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {


    @PostMapping("/testLogin")
    @ResponseBody
    public String testLogin() {
        log.info("/testLogin方法被执行...");
        return "/testLogin方法被执行...";
    }
}
