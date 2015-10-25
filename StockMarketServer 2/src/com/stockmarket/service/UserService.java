package com.stockmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stockmarket.dao.IUserDao;
import com.stockmarket.dao.impl.UserDao;

@Service("userService")
public class UserService {
    @Autowired
    IUserDao userDao;
}
