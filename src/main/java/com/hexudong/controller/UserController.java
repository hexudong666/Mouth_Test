package com.hexudong.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageInfo;
import com.hexudong.cms.utils.entity.FileUtils;
import com.hexudong.cms.utils.entity.HtmlUtils;
import com.hexudong.cms.utils.entity.StringUtils;
import com.hexudong.common.CmsContant;
import com.hexudong.entity.Article;
import com.hexudong.entity.Category;
import com.hexudong.entity.Channel;
import com.hexudong.entity.User;
import com.hexudong.service.ArticleService;
import com.hexudong.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	//登录人事务
	@Autowired
	private UserService service;
	
	//文章事务
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("home")
	private String getList() {
		return "user/home";
	}
	
	@RequestMapping("logout")
	public String home(HttpServletRequest request,HttpServletResponse response) {
		request.getSession().removeAttribute(CmsContant.USER_KEY);
		
		
		Cookie cookieUserName = new Cookie("username", "");
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(0);// 立即过期
		response.addCookie(cookieUserName);
		Cookie cookieUserPwd = new Cookie("userpwd", "");
		cookieUserPwd.setPath("/");
		cookieUserPwd.setMaxAge(0);// 立即过期
		response.addCookie(cookieUserPwd);
		
		return "redirect:/";
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
	public String login(HttpServletRequest request,User user,Model model,HttpServletResponse response) {
		String pwd =  new String(user.getPassword());
		User loginUser = service.login(user);
		
		//登录失败
		if(loginUser==null) {
			request.setAttribute("error", "用户名密码错误");
			return "/user/login";	
		}
		
		System.out.println("loginUser is " + loginUser);
		// 登录成功，用户信息存放看到session当中
		request.getSession().setAttribute(CmsContant.USER_KEY, loginUser);
		model.addAttribute("uname", loginUser);
		
		//保存密码明文到cookice
		//保存用户的用户名和密码
		Cookie cookieUserName = new Cookie("username", user.getUsername());
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(10*24*3600);// 10天天数*每天的小时数*没小时的秒数
		response.addCookie(cookieUserName);
		Cookie cookieUserPwd = new Cookie("userpwd", pwd);
		cookieUserPwd.setPath("/");
		cookieUserPwd.setMaxAge(10*24*3600);// 10天   天数*每天的小时数*没小时的秒数
		response.addCookie(cookieUserPwd);
		
		
		//管理员登录
		// 进入管理界面
		if (loginUser.getRole()==CmsContant.USER_ROLE_ADMIN) {
			 return "redirect:/admin/index";
		}
		
		// 进入个人中心
		return "redirect:/user/home";
	}
	
	/*//在主页退出登陆
	@RequestMapping("logout")
	public String home(HttpServletRequest request) {
		request.getSession().removeAttribute(CmsContant.USER_KEY);
		return "redirect:/";
	}*/
	
	
	/**
	 * 
	    * @Title: username
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
		
		System.out.println("loginuwer jis " + loginUser);
		
		System.out.println("articleService jis " + articleService);
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
	
	
	/**
	 * 
	    * @Title: postArticle
	    * @Description: 发表文章,   查了个栏目
	    * @param @param request
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	//查询
	@RequestMapping("postArticle")
	public String postArticle(HttpServletRequest request) {
		//获取栏目
		List<Channel> channels = articleService.getChannels();
		//往前台发
		request.setAttribute("channels", channels);
		return "user/article/post";
		
	}
	
	
	
	/**
	 * 
	    * @Title: getCategoris
	    * @Description: 根据频道  获取分类
	    * @param @param cid
	    * @param @return    参数
	    * @return List<Category>    返回类型
	    * @throws
	 */
	//查询
	@RequestMapping("getCategoris")
	@ResponseBody
	public List<Category> getCategoris(int cid){
		List<Category> categoris = articleService.getCategorisByCid(cid);
		return categoris;
	}
	
	
	
	/**
	 * 
	    * @Title: postArticle
	    * @Description: 上传文件
	    * @param @param request
	    * @param @param article
	    * @param @param file
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	//文件
	@RequestMapping(value ="postArticle",method=RequestMethod.POST)
	@ResponseBody
	public boolean postArticle(HttpServletRequest request, Article article, MultipartFile file) {
		
		String picUrl;
		try {
			// 处理上传文件
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		article.setUserId(loginUser.getId());
		
		return articleService.add(article)>0;
		
	}

	/**
	 * 
	    * @Title: processFile
	    * @Description: 放到本地
	    * @param @param file
	    * @param @return
	    * @param @throws IllegalStateException
	    * @param @throws IOException    参数
	    * @return String    返回类型
	    * @throws
	 */
	//文件
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {
			// 判断目标目录时间否存在
			//picRootPath + ""
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String subPath = sdf.format(new Date());
			//图片存放的路径
			File path= new File(picRootPath+"/" + subPath);
			//路径不存在则创建
			if(!path.exists())
				path.mkdirs();
			
			//计算新的文件名称
			String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
			
			//随机生成文件名
			String fileName = UUID.randomUUID().toString() + suffixName;
			//文件另存
			file.transferTo(new File(picRootPath+"/" + subPath + "/" + fileName));
			return  subPath + "/" + fileName;
	}
	/***
	 *	 修改
	 */
	//修改
	@RequestMapping(value="updateArticle",method=RequestMethod.GET)
	public String updateArticle(HttpServletRequest request,int id) {	
		
		//获取栏目
		List<Channel> channels= articleService.getChannels();
		request.setAttribute("channels", channels);
		
		//获取文章
		Article article = articleService.getById(id);
		User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		if(loginUser.getId() != article.getUserId()) {
			// todo 准备做异常处理的！！
		}
		request.setAttribute("article", article);
		request.setAttribute("content1",  HtmlUtils.htmlspecialchars(article.getContent()));
		
		return "user/article/update";
	}
	/*
	 * 修改的数据
	 */
	//修改
	@RequestMapping(value="updateArticle",method=RequestMethod.POST)
	@ResponseBody
	public  boolean  updateArticle(HttpServletRequest request,Article article,MultipartFile file) {
		
		System.out.println("aarticle is  "  + article);
		
		String picUrl;
		try {
			// 处理上传文件
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//当前用户是文章的作者
		User loginUser = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		//article.setUserId(loginUser.getId());
		int updateREsult  = articleService.update(article,loginUser.getId());
		return updateREsult>0;
		
	}
	
	
	
	/**
	 * 注销
	 */
	@RequestMapping("loginOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "user/login";
	}
	
	
}
