package com.liang.sessionandnginx.mapper;

import com.liang.sessionandnginx.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {


}
