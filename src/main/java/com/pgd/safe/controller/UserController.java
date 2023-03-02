package com.pgd.safe.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class UserController {
    @GetMapping("user")
    public Object getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("主线程用户信息：" + JSONObject.toJSONString(authentication));

        new Thread(() -> {
            Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("子线程用户信息：" + JSONObject.toJSONString(authentication1));
        }).start();

        return authentication;
    }

    @GetMapping("auth")
    public Object auth(HttpServletRequest req) {
        SecurityContextHolderAwareRequestWrapper request = (SecurityContextHolderAwareRequestWrapper)req;
        log.info("remoteUser：{}", request.getRemoteUser());

        Authentication auth = (Authentication)request.getUserPrincipal();
        log.info("auth.getName={}，auth.getDetails={}", auth.getName(), auth.getDetails().toString());

        return request.getRemoteUser();
    }
}
