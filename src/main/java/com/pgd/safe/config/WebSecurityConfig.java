package com.pgd.safe.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        if(!manager.userExists("pgd")) {
            manager.createUser(User.withUsername("pgd").password("{noop}123").roles("admin").build());
        }
        if(!manager.userExists("dongge")) {
            manager.createUser(User.withUsername("dongge").password("{noop}123").roles("user").build());
        }
        auth.userDetailsService(manager);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("dg").password("{noop}123456").roles("打工人").build());
//        manager.createUser(User.withUsername("dongge").password("{noop}123456").roles("东哥").build());
//        builder.userDetailsService(manager);
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/mylogin.html")
                    .loginProcessingUrl("/doLogin")
                    .defaultSuccessUrl("/index.html")
                    .failureForwardUrl("/mylogin.html")
                    .usernameParameter("uname")
                    .passwordParameter("passwd")
                .permitAll()
                .and()
                .csrf().disable();
    }

    SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setDefaultTargetUrl("/index");
        handler.setTargetUrlParameter("target");
        return handler;
    }

    public static class MySuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");

            JSONObject map = new JSONObject();
            map.put("status", 200);
            map.put("message", "登陆成功");
            response.getWriter().write(map.toJSONString());
        }
    }

}
