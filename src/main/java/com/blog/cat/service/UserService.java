package com.blog.cat.service;

import com.blog.cat.common.exception.UserException;
import com.blog.cat.entity.User;
import lombok.Lombok;

public interface UserService {
    /**
     * 用户登陆
     * @param uid String 用户名
     * @param pwd String 密码
     * @return User
     * @throws UserException
     */
    User login(String uid, String pwd) throws UserException;

    /**
     * 用户注册
     * @param user User
     * @return I
     * @throws UserException
     */
    Integer register(User user) throws  UserException;

    Integer isUidExist(String uid) throws UserException;

    Integer isNicknameExist(String nickname) throws UserException;

    Integer isEmailExist(String email) throws UserException;

    Integer isPhoneExist(Long phone) throws UserException;
}