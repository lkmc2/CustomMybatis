package com.lin.full;


import com.lin.full.core.session.SqlSession;
import com.lin.full.core.session.SqlSessionFactory;
import com.lin.full.mapper.UserMapper;
import com.lin.simple.entity.User;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 自定义Mybatis启动器
 */
public class MybatisStarter {

    public static void main(String[] args) {
        start();
    }

    /**
     * 启动程序
     */
    private static void start() {
        // SQL会话工厂
        SqlSessionFactory factory = new SqlSessionFactory();

        // 打开SQL会话
        SqlSession sqlSession = factory.openSession();

        // 获取用户Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 选择用户测试
        testSelectUser(userMapper);

        // 新增用户测试
//        testInsertUser(userMapper);

        // 更新用户测试
//        testUpdateUser(userMapper);

        // 删除用户测试
//        testDeleteUser(userMapper);
    }

    /**
     * 选择用户测试
     */
    private static void testSelectUser(UserMapper userMapper) {
        // 获取用户信息
        User user = userMapper.selectById(1);

        System.out.println("查询结果：" + user);

        /*
            运行结果：
            查询结果：User{id=1, age=18, name='Jack'}
         */
    }

    /**
     * 新增用户测试
     */
    private static void testInsertUser(UserMapper userMapper) {
        // 插入用户信息
        int effortCount = userMapper.insertUser(4, 23, "Tan");

        showMessage(effortCount, "插入");

        /*
            运行结果：
            插入数据成功，受影响行数1
         */
    }

    /**
     * 更新用户测试
     */
    private static void testUpdateUser(UserMapper userMapper) {
        // 更新用户信息
        int effortCount = userMapper.updateUserName(4, "Candy");

        showMessage(effortCount, "更新");

         /*
            运行结果：
            更新数据成功，受影响行数1
         */
    }

    /**
     * 删除用户测试
     */
    private static void testDeleteUser(UserMapper userMapper) {
        // 删除用户信息
        int effortCount = userMapper.deleteById(4);

        showMessage(effortCount, "删除");

        /*
            运行结果：
            删除数据成功，受影响行数1
         */
    }

    /**
     * 显示提示信息
     * @param hintInfo 提示信息
     */
    private static void showMessage(int effectCount, String hintInfo) {
        if (effectCount > 0) {
            System.out.println(String.format("%s数据成功，受影响行数%d", hintInfo, effectCount));
        } else {
            System.out.println(String.format("%s数据失败", hintInfo));
        }
    }

}
