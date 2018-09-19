package com.lin.executor.impl;

import com.lin.executor.Executor;
import com.lin.executor.User;

import java.sql.*;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 简单数据库查询执行器
 */
public class SimpleExecutor implements Executor {

    /**
     * 执行数据库查询
     * @param sql 查询语句
     * @param parameter 查询参数
     * @param <E> 返回对象类型
     * @return 查询结果
     */
    @SuppressWarnings("unchecked")
    public <E> E query(String sql, Object parameter) {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(String.format(sql, Integer.parseInt(String.valueOf(parameter))));

            ResultSet rs = pstmt.executeQuery();

            User user = new User();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
            }

            return (E) user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数据库连接
     */
    private Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis_test",
                    "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

}
