package com.lin.full.core;

import com.lin.full.core.model.MapperInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description SQL执行器
 */
public class SqlExecuteHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取Mapper信息
        MapperInfo info = getMapperInfo(method);

        // 执行SQL
        return executeSql(info, args);
    }

    /**
     * 获取Mapper信息
     * @param method 方法对象
     * @return Mapper信息
     */
    private MapperInfo getMapperInfo(Method method) throws Exception {
        // 获取接口名和方法名
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        // 根据接口名和方法名获取对应的Mapper信息
        MapperInfo info = SqlMappersHolder.INSTANCE.getMapperInfo(
                className,
                methodName
        );

        if (info == null) {
            throw new Exception(String.format("Mapper未找到该方法：%s.%s", className, methodName));
        }

        return info;
    }

    /**
     * 执行SQL
     * @param info Mapper信息
     * @param args SQL参数
     * @return 执行结果对象
     */
    private Object executeSql(MapperInfo info, Object[] args) {
        return null;
    }

}
