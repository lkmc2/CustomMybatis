package com.lin.simple.proxy;

import com.lin.simple.mapper.UserMapperXml;
import com.lin.simple.session.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description Mapper代理
 */
public class MapperProxy<T> implements InvocationHandler {

    // SQL会话
    private final MySqlSession sqlSession;

    // Mapper接口
    private final Class<T> mapperInterface;

    public MapperProxy(MySqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 当前方法的执行对象名等于Mapper接口名时
        if (method.getDeclaringClass().getName().equals(UserMapperXml.mapperName)) {
            // 获取xml中的SQL查询语句
            String sql = UserMapperXml.methodSqlMapping.get(method.getName());
            System.out.println(String.format("SQL [ %s ], parameter [ %s ]", sql, args[0]));

            // 执行SQL查询一条数据
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }

        return null;
    }

}
