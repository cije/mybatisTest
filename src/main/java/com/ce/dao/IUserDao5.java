package com.ce.dao;

import com.ce.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 注解开发 单表
 */
@CacheNamespace(blocking = true) // 二级缓存
public interface IUserDao5 {
    /**
     * 查询所有操作
     *
     * @return 所有user集合
     */
    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "address", property = "address"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "birthday", property = "birthday"),
    })
    List<User> findAll();

    /**
     * 添加用户
     */
    @Insert("insert into user(username,sex,birthday,address) values(#{username},#{sex},#{birthday},#{address})")
    @ResultMap(value = {"userMap"})
    void saveUser(User user);

    /**
     * 更新用户
     */
    @Update("update user set username=#{username},sex=#{sex},address=#{address},birthday=#{birthday} where id=#{id}")
    @ResultMap(value = {"userMap"})
    void updateUser(User user);

    /**
     * 根据id删除用户
     */
    @Delete("delete from user where id = #{id}")
    @ResultMap(value = {"userMap"})
    void delUser(Integer id);

    /**
     * 根据id查询用户
     */
    @Select("select * from user where id=#{id}")
    @ResultMap(value = {"userMap"})
    User findById(Integer id);

    /**
     * 根据米高程模糊查询用户信息
     */
    // @Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%'")
    @ResultMap(value = {"userMap"})
    List<User> findByName(String username);

    /**
     * 查询总用户数
     *
     * @return 用户个数
     */
    @Select("select count(*) from user")
    @ResultMap(value = {"userMap"})
    int findTotal();
}
