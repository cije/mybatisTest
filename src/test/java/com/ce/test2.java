package com.ce;

import com.ce.dao.IUserDao;
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
 * Mybatis实现增删改查
 */
public class test2 {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

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
        userDao = session.getMapper(IUserDao.class);
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
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("张三");
        user.setSex("男");
        user.setAddress("西安");
        user.setBirthday(LocalDateTime.now());
        System.out.println("保存操作之前：" + user);
        // 保存
        userDao.saveUser(user);
        System.out.println("保存操作之后：" + user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(48);
        user.setUsername("王五");
        user.setSex("男");
        user.setAddress("海南");
        user.setBirthday(LocalDateTime.now());
        // 更新
        userDao.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDel() {
        // 删除
        userDao.delUser(56);
    }

    /**
     * 测试通过id查询
     */
    @Test
    public void testFindById() {
        // 通过id查询
        User user = userDao.findById(50);
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testFindByName() {
        // 通过id查询
        List<User> users = userDao.findByName("%王%");
        // List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal() {
        // 通过id查询
        int count = userDao.findTotal();
        System.out.println(count);
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
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
