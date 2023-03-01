package com.pgd.safe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("index")
    public String index() {
        return "login success";
    }
}
