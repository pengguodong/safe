package com.pgd.safe.mapper;

import com.pgd.safe.entity.Role;
import com.pgd.safe.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    public User getByName(String name);

    public List<Role> getRoles(Integer userId);

}
