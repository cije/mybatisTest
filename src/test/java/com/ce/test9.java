package com.ce;

import com.ce.dao.IAccountDao1;
import com.ce.domain.Account;
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
 * 延迟加载
 */
public class test9 {
    private InputStream in;
    private SqlSession session;
    private IAccountDao1 accountDao1;

    @BeforeEach
    private void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao1 = session.getMapper(IAccountDao1.class);
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
        List<Account> list = accountDao1.findAll();
        list.forEach(System.out::println);
    }
}
