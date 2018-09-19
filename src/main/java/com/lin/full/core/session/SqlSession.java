package com.lin.full.core.session;

import com.lin.full.core.executor.SqlExecuteHandler;

import java.lang.reflect.Proxy;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description SQL会话
 */
public class SqlSession {

    /**
     * 获取Mapper对象
     * @param clazz Mapper接口类
     * @return Mapper对象代理
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{ clazz },
                new SqlExecuteHandler()
        );
    }

}
