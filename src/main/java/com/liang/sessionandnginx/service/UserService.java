package com.liang.sessionandnginx.service;

import com.liang.sessionandnginx.entity.User;
import com.liang.sessionandnginx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username){

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);

        User user = userMapper.selectOneByExample(example);

        return user;
    }


}
