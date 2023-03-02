package com.pgd.safe.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MyLoginController {

    @GetMapping("/mylogin.html")
    public String myLogin() {
        return "mylogin";
    }
}
