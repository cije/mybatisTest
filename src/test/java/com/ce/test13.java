package com.ce;

import com.ce.dao.IAccountDao2;
import com.ce.dao.IUserDao6;
import com.ce.domain.Account;
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
import java.util.List;

/**
 * user Account 一对多 注解Test
 */
public class test13 {
    private InputStream in;
    private SqlSession session;
    private IUserDao6 dao;

    @BeforeEach
    private void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        dao = session.getMapper(IUserDao6.class);
    }

    @AfterEach
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {
        List<User> list = dao.findAll();
        list.forEach(System.out::println);
    }
}
