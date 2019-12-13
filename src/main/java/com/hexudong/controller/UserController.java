package com.hexudong.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.hexudong.cms.utils.entity.StringUtils;
import com.hexudong.common.CmsContant;
import com.hexudong.eitity.Article;
import com.hexudong.eitity.User;
import com.hexudong.service.ArticleService;
import com.hexudong.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private ArticleService   articleService;
	
	@RequestMapping("home")
	private String getList() {
		return "user/home";
	}
	
	/**
	 * 
	    * @Title: register
	    * @Description: 跳到登录界面,并且验证
	    * @param @param request
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	
	
	
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(HttpServletRequest request) {
		User user  = new User();
		request.setAttribute("user", user);
		return "user/register";
	}
	
	/**
	 * 
	    * @Title: register
	    * @Description:从注册页面发过来的请求
	    * @param @param request
	    * @param @param user
	    * @param @param result
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(HttpServletRequest request,
			@Valid @ModelAttribute("user") User user,
			BindingResult result
			) {
		
		// 有错误返回到注册页面
		if(result.hasErrors()) {
			return "user/register";
		}
		//进行唯一性验证
		User existUser = service.getUserByUsername(user.getUsername());
		if(existUser!=null) {
			result.rejectValue("username", "", "用户名已经存在");
			return "user/register";
		}
		
		//验证数字
		//加一个手动的校验
		/*if(StringUtils.isNumber(user.getPassword())) {
			result.rejectValue("passowrd", "", "密码不能全是数字");
			return "user/register";
		}*/
		
		// 去注册
		int reRegister = service.register(user);
		
		//注册失败
		if(reRegister<1) {
			request.setAttribute("eror", "注册失败，请稍后再试！");
			return "user/register";
		}
		//跳转到登录页面
		return "redirect:login";
	}
	
	/**
	 * 
	    * @Title: login
	    * @Description: 跳转login
	    * @param @param request
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		return "user/login";
	}
	/**
	 * 
	    * @Title: login
	    * @Description: 接受login界面的数据
	    * @param @param request
	    * @param @param user
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,User user) {
		User loginUser = service.login(user);
		
		//登录失败
		if(loginUser==null) {
			request.setAttribute("error", "用户名密码错误");
			return "/user/login";	
		}
		
		// 登录成功，用户信息存放看到session当中
		request.getSession().setAttribute(CmsContant.USER_KEY, loginUser);
		
		// 进入管理界面
		if (loginUser.getRole()==CmsContant.USER_ROLE_ADMIN) {
			 return "redirect:/admin/index";
		}
		
		// 进入个人中心
		return "redirect:/user/home";
		
	}
	
	/**
	 * 
	    * @Title: checkUserName
	    * @Description: 根据用户名查找用户
	    * @param @param username
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	@RequestMapping("checkname")
	@ResponseBody
	public boolean checkUserName(String username) {
		User existUser = service.getUserByUsername(username);
		return existUser==null;
	}
	
	/**
	 * 	删除文章
	 */
	
	@RequestMapping("deletearticle")
	@ResponseBody
	public boolean deleteArticle(int id) {
		int result = articleService.delete(id);
		return result>0;
	}
	
	/**
	 * 
	    * @Title: articles
	    * @Description: 去往文章列表
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	//去往文章列表
	@RequestMapping("articles")
	public String articles(HttpServletRequest request,@RequestParam(defaultValue="1")int page) {
		//获取登录的用户
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		//分页   Article 文章
		PageInfo<Article> articlePage = articleService.listByUser(loginUser.getId(),page);
		//发送到前台
		request.setAttribute("articlePage", articlePage);
		return "user/article/list";
	}
	
	/**
	 * 
	    * @Title: comments
	    * @Description: 去往评论列表
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	//去往评论列表
	@RequestMapping("comments")
	public String comments() {
		return "user/comment/list";
	}
	
	
	
	
}
