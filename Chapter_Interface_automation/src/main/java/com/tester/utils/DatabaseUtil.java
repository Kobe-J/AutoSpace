package com.tester.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
/**
 * 2020.5.9
 * @author yxl
 *
 * */
public class DatabaseUtil {

    public static SqlSession getSqlSession() throws IOException {
        //获取配置的资源文件
        Reader reader = Resources.getResourceAsReader("databaseConfig.xml");
        //SqlSessionFactory，使用类加载器加载xml文件
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
        //sqlsession对象，执行配置文件中的sql语句
        SqlSession session = factory.openSession();

        return session;
    }
}
