package com.ce.dao;

import com.ce.domain.Account;
import com.ce.domain.AccountUser;

import java.util.List;

/**
 * 帐户 用户 多对一（一对一）
 */
public interface IAccountDao {
    /**
     * 查询所有帐户，同时获取当前帐户的所属用户信息
     */
    List<Account> findAll();

    /**
     * 查询所有帐户，并且带有用户名称和地址信息
     */
    List<AccountUser> findAllAccount();

    /**
     * 根据用户id查询帐户
     * @param uid
     * @return
     */
    List<Account> findAccountByUid(Integer uid);
}
