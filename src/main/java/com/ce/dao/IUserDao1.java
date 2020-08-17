package com.ce.dao;

import com.ce.domain.QueryVo;
import com.ce.domain.User;

import java.util.List;

/**
 * 用户查询 动态sql
 */
public interface IUserDao1 {
    /**
     * 查询所有操作
     *
     * @return 所有user集合
     */
    // @Select("select * from user")
    List<User> findAll();

    /**
     * 根据id查询用户
     */
    User findById(Integer id);

    /**
     * 根据米高程模糊查询用户信息
     */
    List<User> findByName(String username);

    /**
     * 根据queryVo中的条件查询
     *
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     * @param user 查询的条件
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据queryvo中提供的id集合查询用户信息
     */
    List<User> findUserInIds(QueryVo vo);
}
