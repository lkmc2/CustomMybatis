# CustomMybatis
一个自定义的Mybatis，可实现对MySQL数据库的增删改查功能。

## 启动方式

运行MybatisStarter类的main方法。
```java
public class MybatisStarter {

    public static void main(String[] args) {
        start();
    }

    ......
}
```

## 关键配置

1.在MySQL数据库中创建名为mybatis_test的数据库，并在数据库中运行resources目录下的user.sql文件。

2.修改com.lin.full.core.config.Config文件中数据库相关属性。
```java
public class Config {

    // 数据库配置信息
    private String url = "jdbc:mysql://localhost:3306/mybatis_test?useSSL=false";
    private String username = "root";
    private String password = "123456";
```

3.运行MybatisStarter类的main方法。