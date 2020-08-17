package com.ce;

import com.ce.dao.IUserDao7;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 动态sql 注解方式
 */
public class test14 {
    private InputStream in;
    private SqlSession session;
    private IUserDao7 userDao7;

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
        userDao7 = session.getMapper(IUserDao7.class);
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
        List<User> users = userDao7.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByQueryVo() {
        // 通过id查询
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao7.findUserByVo(vo);
        users.forEach(System.out::println);
    }

    /**
     * 条件查询
     */
    @Test
    public void testFindByCondition() {
        User user = new User();
        // user.setUsername("老王");
        user.setSex("女");
        List<User> users = userDao7.findUserByCondition(user);
        users.forEach(System.out::println);
    }

    /**
     * 测试foreach标签的使用
     */
    @Test
    public void testFindInIds() {
        QueryVo vo = new QueryVo();
        List<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(42);
        list.add(43);
        list.add(44);
        vo.setIds(list);
        List<User> users = userDao7.findUserInIds(vo);
        users.forEach(System.out::println);
    }

}
