package com.ce;

import com.ce.dao.IAccountDao;
import com.ce.domain.Account;
import com.ce.domain.AccountUser;
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
 * Account user 多对一（一对一） Test
 */
public class test5 {
    private InputStream in;
    private SqlSession session;
    private IAccountDao accountDao;

    @BeforeEach
    private void init() throws IOException {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);
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
        List<Account> list = accountDao.findAll();
        list.forEach(System.out::println);
    }

    /**
     * 测试查询所有帐户，并且带有用户名称和地址信息
     */
    @Test
    public void testFindAllAccount() {
        List<AccountUser> list = accountDao.findAllAccount();
        list.forEach(System.out::println);
    }
}
