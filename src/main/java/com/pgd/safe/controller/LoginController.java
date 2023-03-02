package com.pgd.safe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("index.html")
    public String index() {
        return "login success";
    }

    @GetMapping("hello")
    public String hello() {
        return "hello srping security";
    }



    @GetMapping("gaga")
    public String gaga() {
        return "嘎嘎";
    }


    @GetMapping("hehe")
    public String hehe() {
        return "嘿嘿";
    }

}
