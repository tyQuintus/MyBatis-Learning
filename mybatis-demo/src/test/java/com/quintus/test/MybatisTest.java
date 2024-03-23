package com.quintus.test;

import com.quintus.mapper.EmployeeMapper;
import com.quintus.pojo.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


public class MybatisTest {

    @Test
    public void test() throws IOException {
        // 1.创建SqlSessionFactory对象
        //   - 声明Mybatis全局配置文件的路径
        String mybatisConfigFilePath = "mybatis-config.xml";
        //   - 以输入流的形式加载Mybatis配置文件
        //     org.apache.ibatis.io.Resources
        InputStream inputStream = Resources.getResourceAsStream(mybatisConfigFilePath);
        //   - 基于读取Mybatis配置文件的输入流创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.使用SqlSessionFactory对象开启一个对话
        SqlSession session = sessionFactory.openSession();

        // 3.根据EmployeeMapper接口的Class对象获取Mapper接口类型的对象（动态代理技术）
        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);

        // 4.调用代理类方法即可以触发对应的sql语句
        Employee employee = employeeMapper.queryById(1);
        System.out.println("employee = " + employee);

        // 5.关闭SqlSession
        session.commit();
        session.close();

    }
}
