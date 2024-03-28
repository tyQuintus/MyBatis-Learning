package com.quintus.test;

import com.quintus.mapper.UserMapper;
import com.quintus.pojo.User;
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

public class MyBatisTest {

    private SqlSession sqlSession;

    @BeforeEach
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession(true); // 开启自动提交
    }

    @AfterEach
    public void clean(){
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testInsert() throws IOException {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("admin");
        user.setPassword("123456");
        int insert = mapper.insert(user);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testUpdate(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        user.setName("root");
        user.setPassword("12345678");
        int update = mapper.update(user);
        System.out.println("update = " + update);
    }

    @Test
    public void testDelete(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int delete = mapper.delete(1);
        System.out.println("delete = " + delete);
    }

    @Test
    public void testSelectById(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.selectById(1);
        System.out.println("user = " + user);
    }

    @Test
    public void testSelectAll(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.selectAll();
        System.out.println("list = " + list);
    }
}
