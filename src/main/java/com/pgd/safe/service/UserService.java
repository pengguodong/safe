package com.pgd.safe.service;

import com.pgd.safe.dao.UserDao;
import com.pgd.safe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

//    @Autowired
//    UserMapper userMapper;

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        return user;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userMapper.getByName(username);
//        if(null == user) {
//            throw new RuntimeException("用户不存在");
//        }
//        user.setRoles(userMapper.getRoles(user.getId()));
//        return user;
//    }
}
