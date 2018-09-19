package com.lin.full.core;

import com.lin.full.core.model.MapperInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

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
        MapperInfo info = SqlMappersHolder.INSTANCE.getMapperInfo(className, methodName);

        if (info == null) {
            throw new Exception(String.format("Mapper未找到该方法：%s.%s", className, methodName));
        }

        return info;
    }

    /**
     * 执行SQL
     * @param info   Mapper信息
     * @param params SQL参数
     * @return 执行结果对象
     */
    private Object executeSql(MapperInfo info, Object[] params) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 执行结果对象
        Object result;

        // 获取数据库连接
        Connection conn = ConnectionManager.getConnection();

        // 获取Mapper信息中的SQL语句，并设置参数
        PreparedStatement pstmt = conn.prepareStatement(info.getSql());
        for (int i = 0; i < params.length; i++) {
            pstmt.setObject(i + 1, params[i]);
        }

        // 根据查询类型处理
        switch (info.getQueryType()) {
            case SELECT: {
                result = handleSelectEvent(pstmt, info);
                break;
            }
            case INSERT: {
                result = handleInsertEvent(pstmt, info);
                break;
            }
            case UPDATE: {
                result = handleUpdateEvent(pstmt, info);
                break;
            }
            case DELETE: {
                result = handleDeleteEvent(pstmt, info);
                break;
            }
            default:
                throw new RuntimeException("执行类型错误");
        }

        pstmt.close();
        // 关闭数据库连接
        ConnectionManager.close(conn);
        return result;
    }

    /**
     * 处理查询事件
     * @param pstmt 查询状态
     * @param info  Mapper信息
     */
    private Object handleSelectEvent(PreparedStatement pstmt, MapperInfo info) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object result = null;

        // 执行SQL获取查询结果集合
        ResultSet rs = pstmt.executeQuery();

        // 移动到集合第一个元素
        boolean hasResult = rs.first();

        if (hasResult) {
            // 将查询结果映射为基本类型（只支持int和String），或者指定Java类类型
            if (rs.getMetaData().getColumnCount() == 1) {
                switch (info.getResultType()) {
                    case "int":
                        result = rs.getInt(1);
                        break;
                    default:
                        result = rs.getString(1);
                }
            } else {
                // 获取返回结果的类型
                Class<?> resultTypeClass = Class.forName(info.getResultType());

                // 结果返回结果实例对象
                Object obj = resultTypeClass.newInstance();

                // 遍历返回结果类型中的属性
                for (Field field : resultTypeClass.getDeclaredFields()) {
                    // 获取Setter方法的名字
                    String setterName = "set" + toTitle(field.getName());

                    // 获取属性类型
                    Class<?> fieldType = field.getType();

                    // 获取Setter方法
                    Method method = resultTypeClass.getMethod(setterName, fieldType);

                    // 根据属性类型给Setter传参
                    if ("Integer".equals(fieldType.getSimpleName())) {
                        method.invoke(obj, rs.getInt(field.getName()));
                    } else {
                        method.invoke(obj, rs.getString(field.getName()));
                    }
                }
                result = obj;
            }
        } else {
            System.out.println("未查询到结果");
        }

        return result;
    }

    /**
     * 将单词的首字母变成大写
     */
    private String toTitle(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    /**
     * 处理插入事件
     */
    private int handleInsertEvent(PreparedStatement pstmt, MapperInfo info) throws SQLException {
        return pstmt.executeUpdate();
    }

    /**
     * 处理更新事件
     */
    private int handleUpdateEvent(PreparedStatement pstmt, MapperInfo info) throws SQLException {
        return pstmt.executeUpdate();
    }

    /**
     * 处理删除事件
     */
    private int handleDeleteEvent(PreparedStatement pstmt, MapperInfo info) throws SQLException {
        return pstmt.executeUpdate();
    }

}
