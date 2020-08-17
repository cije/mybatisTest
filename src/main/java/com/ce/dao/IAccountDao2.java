package com.ce.dao;

import com.ce.domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 注解开发
 */
public interface IAccountDao2 {
    /**
     * 查询所有帐户，同时获取当前帐户的所属用户信息
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(property = "user", column = "uid", one = @One(fetchType = FetchType.EAGER, select = "com.ce.dao.IUserDao5.findById"))
    })
    List<Account> findAll();

    /**
     * 根据用户id查询帐户
     */
    @Select("select * from account where uid=#{uid}")
    List<Account> findAccountById(Integer uid);
}
