package com.hexudong.service;

import javax.validation.Valid;

import com.hexudong.eitity.User;

public interface UserService {

	User getUserByUsername(String username);

	int register(@Valid User user);

}
