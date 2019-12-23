package com.hexudong.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.hexudong.common.CmsContant;
import com.hexudong.entity.User;
import com.hexudong.service.UserService;

public class CmsInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// TODO Auto-generated method stub
		User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		if(loginUser != null)
		{
			return true;
		}
		
		//获取cookice中的用户信息
		User user = new User();
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if("username".equals(cookies[i].getName())){
				user.setUsername(cookies[i].getValue());
			}
			if("userpwd".equals(cookies[i].getName())){
				user.setPassword(cookies[i].getValue());
			}
		}
		
		
		// 说明cookie中存放的用户信息不完整
		if(null==user.getUsername() || null== user.getPassword()) {
			//request.s
			response.sendRedirect("/user/login");
			//request.getRequestDispatcher("/user/login").forward(request, response);
			return false;
		}
		 
		// 利用cookie中用户信息进行登录操作
		loginUser = service.login(user);
		if(loginUser!=null) {
			request.getSession().setAttribute(CmsContant.USER_KEY, loginUser);
			return true;
		}
		//不对就返回登录界面
		response.sendRedirect("/user/login");
		return false;
		
	}
		
}
