# CustomMybatis
一个自定义的Mybatis，可实现对MySQL数据库的增删改查功能。


3.运行MybatisStarter类的main方法。

## 实现思路

1. 对Mapper接口和Mapper.xml文件进行扫描，存储接口和相关SQL属性到一个Map中。
2. SqlSession使用动态代理，将Mapper接口信息传给代理对象Proxy。
3. 代理对象Proxy从Map从Map获取该接口对应的查询语句，交给SqlSession。
4. SqlSession将查询语句交给Executor进行数据库查询，并获取返回的数据，交给Mapper接口。

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
    
    ......
}
```
