package com.lin.simple.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description xml模拟文件（相当于Configuration，会读取并保存xml和对应的Mapper接口信息）
 */
public class UserMapperXml {

    // 对应的Mapper接口名
    public static final String mapperName = "com.lin.simple.mapper.UserMapper";

    // 接口方法和xml对应的查询语句的映射
    public static final Map<String, String> methodSqlMapping = new HashMap<>();

    static {
        methodSqlMapping.put("selectById", "select * from user where id = %d");
    }

}
