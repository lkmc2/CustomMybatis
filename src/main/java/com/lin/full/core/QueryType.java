package com.lin.full.core;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 查询类型枚举
 */
public enum  QueryType {
    SELECT,
    UPDATE,
    INSERT,
    DELETE;

    /**
     * 根据名字获取对应的枚举
     * @param typeName 类型名
     * @return 对应的枚举值
     */
    public static QueryType value(String typeName) {
        return valueOf(typeName.toUpperCase());
    }

}
