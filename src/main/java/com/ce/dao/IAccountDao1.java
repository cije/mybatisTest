package com.ce.dao;

import com.ce.domain.Account;

import java.util.List;

/**
 * 延迟加载
 */
public interface IAccountDao1 {
    /**
     * 查询所有帐户，同时获取当前帐户的所属用户信息
     */
    List<Account> findAll();
}
