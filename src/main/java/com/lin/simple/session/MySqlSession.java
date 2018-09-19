package com.lin.simple.session;

import com.lin.simple.executor.Executor;
import com.lin.simple.executor.impl.SimpleExecutor;
import com.lin.simple.proxy.MapperProxy;

import java.lang.reflect.Proxy;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description SQL会话
 */
public class MySqlSession {

    // SQL执行器
    private Executor executor = new SimpleExecutor();

    /**
     * 查询一条数据
     * @param sql 查询数据
     * @param parameter 查询参数
     * @param <T> 返回实体类型
     * @return 执行查询的结果
     */
    public <T> T selectOne(String sql, Object parameter) {
        return executor.query(sql, parameter);
    }

    /**
     * 获取Mapper接口对象实例
     * @param clazz Mapper接口类型
     * @return Mapper接口对象实例
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                                            new Class[]{ clazz },
                                            new MapperProxy<T>(this, clazz));
    }

}
