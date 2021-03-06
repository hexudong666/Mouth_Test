package com.hexudong.service;

import javax.validation.Valid;

import com.hexudong.entity.User;

public interface UserService {

	User getUserByUsername(String username);

	int register(@Valid User user);

	User login(User user);

}
