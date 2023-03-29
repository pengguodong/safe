package com.pgd.safe;

import com.alibaba.fastjson.JSONObject;
import com.pgd.safe.dao.UserDao;
import com.pgd.safe.entity.Role;
import com.pgd.safe.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
class SafeApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    public void testjm() {
        PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(pe.encode("123456"));
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void addUser() {
        User u1 = new User();
        u1.setUsername("dg");
        u1.setPassword("{noop}123");
        u1.setAccountNonExpired(true);
        u1.setAccountNonLocked(true);
        u1.setCredentialsNonExpired(true);
        u1.setEnabled(true);

        List<Role> rs1 = new ArrayList<>();
        Role r1 = new Role();
        r1.setName("ROLE_admin");
        r1.setNameZh("管理员");
        rs1.add(r1);
        u1.setRoles(rs1);
        userDao.save(u1);
    }

    @Test
    public void testUser() {
        User pgd = userDao.findUserByUsername("pgd");
        System.out.println(JSONObject.toJSONString(pgd));
    }

}
