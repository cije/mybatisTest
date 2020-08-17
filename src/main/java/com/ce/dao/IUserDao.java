package com.ce.dao;

import com.ce.domain.QueryVo;
import com.ce.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     *
     * @return 所有user集合
     */
    // @Select("select * from user")
    List<User> findAll();

    /**
     * 添加用户
     */
    void saveUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     */
    void delUser(Integer id);

    /**
     * 根据id查询用户
     */
    User findById(Integer id);

    /**
     * 根据米高程模糊查询用户信息
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return 用户个数
     */
    int findTotal();

    /**
     * 根据queryVo中的条件查询
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);
}
