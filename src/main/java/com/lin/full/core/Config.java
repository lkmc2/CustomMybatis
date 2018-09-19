package com.lin.full.core;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 配置信息
 */
public class Config {

    // 配置信息实例
    public static final Config DEFAULT = new Config();

    // 数据库配置信息
    private String url = "jdbc:mysql://localhost:3306/mybatis_test?useSSL=false";
    private String username = "root";
    private String password = "123456";

    // Mapper xml文件所在文件夹
    private String mapperPath = "mapper/";

    private Config() {
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMapperPath() {
        return mapperPath;
    }

}
