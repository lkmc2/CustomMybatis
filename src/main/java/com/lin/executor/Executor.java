package com.lin.executor;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 数据库查询执行器接口
 */
public interface Executor {
    /**
     * 执行数据库查询
     * @param sql 查询语句
     * @param parameter 查询参数
     * @param <E> 返回对象类型
     * @return 查询结果
     */
    <E> E query(String sql, Object parameter);
}
