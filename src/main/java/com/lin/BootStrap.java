package com.lin;

import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.session.MySqlSession;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 自定义MyBatis启动类
 */
public class BootStrap {

    /**
     * 启动项目
     */
    public static void start() {
        // SQL会话
        MySqlSession sqlSession = new MySqlSession();

        // 从会话中获取Mapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 根据id获取用户
        User user = userMapper.selectById(1);

        System.out.println(user);

        /*
            运行结果：
            SQL [ select * from user where id = %d ], parameter [ 1 ]
            User{id=1, age=18, name='Jack'}
         */
    }

    public static void main(String[] args) {
        start();
    }

}
