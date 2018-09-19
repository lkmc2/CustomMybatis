package com.lin.full.core;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description SQL会话工厂
 */
public class SqlSessionFactory {

    static {
        // 创建SqlMappersHolder实例
        SqlMappersHolder instance = SqlMappersHolder.INSTANCE;

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打开SQL会话
     */
    public SqlSession openSession() {
        return new SqlSession();
    }

}
