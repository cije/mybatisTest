package com.ce;

import com.ce.dao.IUserDao1;
import com.ce.dao.IUserDao5;
import com.ce.domain.QueryVo;
import com.ce.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Mybatis 注解开发 单表
 */
public class test11 {
    private InputStream in;
    private SqlSession session;
    private IUserDao5 dao5;

    @BeforeEach
    private void init() throws IOException {
        // 1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 3.使用工厂生产SqlSession对象
        //true : 自动提交
        session = factory.openSession(true);
        // 4.使用SqlSession创建Dao接口的代理对象
        dao5 = session.getMapper(IUserDao5.class);
    }

    @AfterEach
    private void destory() throws IOException {
        //提交事务
        // session.commit();
        // 6.释放资源
        session.close();
        in.close();
    }

    /**
     * 测试查询
     */
    @Test
    public void testSelect() {
        // 5.使用代理对象执行查询方法
        List<User> users = dao5.findAll();
        users.forEach(System.out::println);
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("注解测试");
        user.setSex("男");
        user.setAddress("注解");
        user.setBirthday(LocalDateTime.now());
        System.out.println("保存操作之前：" + user);
        // 保存
        dao5.saveUser(user);
        System.out.println("保存操作之后：" + user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(57);
        user.setUsername("注解1");
        user.setSex("男");
        user.setAddress("海南");
        user.setBirthday(LocalDateTime.now());
        // 更新
        dao5.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDel() {
        // 删除
        dao5.delUser(58);
    }

    /**
     * 测试通过id查询
     */
    @Test
    public void testFindById() {
        // 通过id查询
        User user = dao5.findById(50);
        System.out.println(user);
        // session.clearCache();
        // 通过id查询
        User user1 = dao5.findById(50);
        System.out.println(user1);
        System.out.println(user==user1);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByName() {
        // 通过id查询
        // List<User> users = dao5.findByName("%王%");
        List<User> users = dao5.findByName("王");
        users.forEach(System.out::println);
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal() {
        // 通过id查询
        int count = dao5.findTotal();
        System.out.println(count);
    }
}
