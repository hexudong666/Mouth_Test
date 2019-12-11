package com.hexudong.mapper;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.hexudong.eitity.User;

public interface UserMapper {
	/**
	 * 
	    * @Title: findUserByName
	    * @Description: 根据用户名查找用户
	    * @param @param username
	    * @param @return    参数
	    * @return User    返回类型
	    * @throws
	 */
	@Select(" SELECT id,username,password FROM cms_user "
			+ " WHERE username = #{value} limit 1")
	User findUserByName(String username);

	
	
	/**
	 * 
	    * @Title: add
	    * @Description: 添加用户
	    * @param @param user
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	@Insert("INSERT INTO cms_user(username,password,locked,create_time,score,role)"
			+ " VALUES(#{username},#{password},0,now(),0,0)")
	int add(@Valid User user);

}
