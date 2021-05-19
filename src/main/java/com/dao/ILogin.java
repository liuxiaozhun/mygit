package com.dao;

import com.pojo.User;

import java.util.List;

/**
 * 登录相关的数据操作
 * 。。。写的
 * 日期。。。
 */
public interface ILogin {
    int COM_OK = 1;
    int COM_ERROR = 2;

    /**
     * 登录
     * @param u  用户对象
     * @return
     */
    public User login(User u);
    public int reg(User u);
    public List<User> getAllUser();
}
