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
		
		String encryPwd =CmsUtils.encry(user.getPassword(),user.getUsername().substring(2,4));
		user.setPassword(encryPwd);
		return mapper.add(user);
	}
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
				user.setPassword(CmsUtils.encry(user.getPassword(), user.getUsername() ));
				User loginUser  = mapper.findByPwd(user);
				return loginUser;
	}
	
}
