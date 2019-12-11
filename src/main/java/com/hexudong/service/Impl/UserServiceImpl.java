package com.hexudong.service.Impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexudong.common.CmsUtils;
import com.hexudong.eitity.User;
import com.hexudong.mapper.UserMapper;
import com.hexudong.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	/**
	 * 
	 */
	@Override
	public User getUserByUsername(String username) {
		return mapper.findUserByName(username);
	}
	
	@Override
	public int register(@Valid User user) {
		/*到这了**/
		String encryPwd =CmsUtils.encty(user.getPassword(),user.getUsername().substring(2,4));
		user.setPassword(encryPwd);
		return mapper.add(user);
	}
	
}
