package com.ce.dao.impl;

import com.ce.dao.IUserDao;
import com.ce.domain.QueryVo;
import com.ce.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSessionFactory factory;
    private final String daoName = "com.com.ce.dao.IUserDao";

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    /**
     * 查询所有操作
     *
     * @return 所有user集合
     */
    @Override
    public List<User> findAll() {
        List<User> users = null;
        //1.使用工厂创建SqlSession对象
        try (SqlSession session = factory.openSession()) {
            //2.使用session执行查询
            users = session.selectList(daoName + ".findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.返回查询结果
        return users;
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        try (SqlSession session = factory.openSession()) {
            session.insert(daoName + ".saveUser", user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新用户
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        try (SqlSession session = factory.openSession()) {
            session.update(daoName + ".updateUser", user);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id删除用户
     *
     * @param id
     */
    @Override
    public void delUser(Integer id) {
        try (SqlSession session = factory.openSession()) {
            session.delete(daoName + ".delUser", id);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询用户
     *
     * @param id
     */
    @Override
    public User findById(Integer id) {
        User user = null;
        //1.使用工厂创建SqlSession对象
        try (SqlSession session = factory.openSession()) {
            //2.使用session执行查询
            user = session.selectOne(daoName + ".findById", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 根据米高程模糊查询用户信息
     *
     * @param username
     */
    @Override
    public List<User> findByName(String username) {
        List<User> users = null;
        //1.使用工厂创建SqlSession对象
        try (SqlSession session = factory.openSession()) {
            //2.使用session执行查询
            users = session.selectList(daoName + ".findByName", username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.返回查询结果
        return users;
    }

    /**
     * 查询总用户数
     *
     * @return 用户个数
     */
    @Override
    public int findTotal() {
        Integer count = 0;
        //1.使用工厂创建SqlSession对象
        try (SqlSession session = factory.openSession()) {
            //2.使用session执行查询
            count = session.selectOne(daoName + ".findTotal");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.返回查询结果
        return count;
    }

    /**
     * 根据queryVo中的条件查询
     *
     * @param vo
     * @return
     */
    @Override
    public List<User> findUserByVo(QueryVo vo) {
        List<User> users = null;
        //1.使用工厂创建SqlSession对象
        try (SqlSession session = factory.openSession()) {
            //2.使用session执行查询
            users = session.selectList(daoName + ".findUserByVo", vo.getUser().getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.返回查询结果
        return users;
    }
}
