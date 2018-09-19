package com.lin.full.mapper;

import com.lin.simple.entity.User;

/**
 * @author lkmc2
 * @date 2018/9/19
 * @description 用户查询接口
 */
public interface UserMapper {

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return id对应的用户
     */
    User getUserInfo(int id);

    /**
     * 更新用户名
     * @param id 用户id
     * @param userName 用户名
     * @return 受影响行数
     */
    int updateUserName(int id, String userName);

    /**
     * 插入用户
     * @param id 用户id
     * @param age 年龄
     * @param name 用户名
     * @return 受影响行数
     */
    int insertUser(int id, int age, String name);

}
