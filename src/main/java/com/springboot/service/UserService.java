package com.springboot.service;

import com.springboot.bean.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getUserByName(String name);
}
