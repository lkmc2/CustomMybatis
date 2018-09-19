package com.lin.executor.mapper;

import com.lin.executor.User;

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
    User selectById(int id);
}
