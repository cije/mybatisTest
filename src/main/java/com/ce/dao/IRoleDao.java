package com.ce.dao;

import com.ce.domain.Role;

import java.util.List;

/**
 * 角色 用户 多对多
 */
public interface IRoleDao {
    /**
     * 查询所有角色，并该处角色的所有用户
     */
    List<Role> findAll();
}
