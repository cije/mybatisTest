package com.ce.dao;

import com.ce.domain.User;

import java.util.List;

/**
 * 用户 帐户 多对一
 */
public interface IUserDao4 {
    /**
     * 查询所有用户，同时获取到用户下所有帐户信息
     * @return
     */
    List<User> findAll();
}
