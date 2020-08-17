package com.ce.dao;

import com.ce.domain.User;

import java.util.List;

/**
 * 用户 和 角色 多对多
 */
public interface IUserDao3 {
    /**
     * 查询所有用户，
     */
    List<User> findAll();

}
