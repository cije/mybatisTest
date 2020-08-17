package com.ce;

import com.ce.dao.IUserDao2;
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
 * 多对一 User account test
 */
public class test6 {
    private InputStream in;
    private SqlSession session;
    private IUserDao2 userDao2;

    @BeforeEach
    private void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao2 = session.getMapper(IUserDao2.class);
    }

    @AfterEach
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 查询所有用户，同时获取到用户下所有帐户信息
     */
    @Test
    public void testFindAll() {
        List<User> list = userDao2.findAll();
        list.forEach(System.out::println);
    }
}
