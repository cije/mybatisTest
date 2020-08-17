package com.ce.dao;

import com.ce.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 用户 帐户一对多 注解
 */
public interface IUserDao6 {
    /**
     * 查询所有用户，同时获取到用户下所有帐户信息
     *
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "username", property = "username"),
            @Result(property = "accounts", column = "id", many = @Many(select = "com.ce.dao.IAccountDao2.findAccountById", fetchType = FetchType.LAZY))
    })
    List<User> findAll();
}
