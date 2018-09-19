package com.lin.full.core;

import com.lin.full.core.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 数据库连接控制器
 */
public class ConnectionManager {

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.DEFAULT.getUrl(),
                Config.DEFAULT.getUsername(),
                Config.DEFAULT.getPassword()
        );
    }

    /**
     * 关闭数据库连接
     * @param conn 数据库连接
     */
    public static void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

}
