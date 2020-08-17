package com.ce;

import com.ce.dao.IUserDao3;
import com.ce.domain.Role;
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
 * User Role多对多
 */
public class test8 {
    private InputStream in;
    private SqlSession session;
    private IUserDao3 userDao3;

    @BeforeEach
    private void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao3 = session.getMapper(IUserDao3.class);
    }

    @AfterEach
    public void destory() throws IOException {
        session.commit();
        session.close();
        in.close();
    }

    /**
     * 查询所有用户信息及 对应的角色集合
     */
    @Test
    public void testFindAll() {
        List<User> list = userDao3.findAll();
        list.forEach(System.out::println);
    }
}
