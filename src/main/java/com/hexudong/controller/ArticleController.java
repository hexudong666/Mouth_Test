package com.hexudong.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.cms.utils.entity.StringUtils;
import com.hexudong.common.CmsContant;
import com.hexudong.common.CmsError;
import com.hexudong.common.CmsMessage;
import com.hexudong.entity.Article;
import com.hexudong.entity.Comment;
import com.hexudong.entity.Complain;
import com.hexudong.entity.User;
import com.hexudong.service.ArticleService;


@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {
	
	@Autowired
	ArticleService articleService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("getDetail")
	@ResponseBody
	public CmsMessage getDetail(int id) {
		if(id<=0) {
			
		}
		// 获取文章详情
		Article article = articleService.getById(id);
		// 不存在
		if(article==null)
			return new CmsMessage(CmsError.NOT_EXIST, "文章不存在",null);
		
		// 返回数据
		return new CmsMessage(CmsError.SUCCESS,"",article); 
	}

	
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,int id) {
		
		Article article = articleService.getById(id);
		request.setAttribute("article", article);
		return "detail";
		
	}
	
	
	@RequestMapping("postcomment")
	@ResponseBody
	public CmsMessage postcomment(HttpServletRequest request,int articleId,String content) {
		
		User loginUser  = (User)request.getSession().getAttribute(CmsContant.USER_KEY);
		
		if(loginUser==null) {
			return new CmsMessage(CmsError.NOT_LOGIN, "您尚未登录！", null);
		}
		
		Comment comment = new Comment();
		comment.setUserId(loginUser.getId());
		comment.setContent(content);
		comment.setArticleId(articleId);
		int result = articleService.addComment(comment);
		if(result > 0)
			return new CmsMessage(CmsError.SUCCESS, "成功", null);
		
		return new CmsMessage(CmsError.FAILED_UPDATE_DB, "异常原因失败，请与管理员联系", null);
		
	}
	
		@RequestMapping("comments")
		public String comments(HttpServletRequest request,int id,int page) {
			PageInfo<Comment> commentPage =  articleService.getComments(id,page);
			request.setAttribute("commentPage", commentPage);
			return "comments";
		}
	
		
		/**
		 * 
		    * @Title: complain
		    * @Description: 跳转到投诉的页面
		    * @param @param request
		    * @param @param articleId
		    * @param @return    参数
		    * @return String    返回类型
		    * @throws
		 */
		@RequestMapping(value="complain",method=RequestMethod.GET)
		public String complain(HttpServletRequest request,int articleId) {
			Article article= articleService.getById(articleId);
			request.setAttribute("article", article);
			request.setAttribute("complain", new Complain());
			return "article/complain";
					
		}
		
		/**
		 * 接受投诉页面提交的数据
		 * @param request
		 * @param articleId
		 * @return
		 * @throws IOException 
		 * @throws IllegalStateException 
		 */
		@RequestMapping(value="complain",method=RequestMethod.POST)
		public String complain(HttpServletRequest request,@ModelAttribute("complain") @Valid Complain complain,MultipartFile file,BindingResult result) throws IllegalStateException, IOException {
			
			if(!StringUtils.isurl(complain.getSrcUrl())) {
				result.rejectValue("srcUrl", "", "不是合法的url地址");
			}
			if(result.hasErrors()) {
				return "article/complain";
			}
			
			User loginUser  =  (User)request.getSession().getAttribute(CmsContant.USER_KEY);
			
			
			/*String picUrl = this.processFile(file);
			complain.setPicture(picUrl);*/
			
			
			//加上投诉人
			if(loginUser!=null)
				complain.setUserId(loginUser.getId());
			else
				complain.setUserId(0);
			
			articleService.addComplian(complain);
			
			return "redirect:/article/detail?id="+complain.getArticleId();
					
		}
		
		
		
		//全显示
		//complains?articleId
			@RequestMapping("complains")
			public String 	complains(HttpServletRequest request,int articleId,@RequestParam(defaultValue="1") int page) {
				PageInfo<Complain> complianPage=   articleService.getComplains(articleId, page);
				request.setAttribute("complianPage", complianPage);
				return "article/complainslist";
			}
		
			
			//投诉详情
			@RequestMapping(value="getComplains",method=RequestMethod.GET)
			public String getComplain(HttpServletRequest request,int articleId,Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="1")int pageNum) {
				Article article= articleService.getById(articleId);
				request.setAttribute("article", article);
				request.setAttribute("complain", new Complain());
				PageInfo<Complain> list = articleService.getWZComplains(articleId, page);
				model.addAttribute("c", list);
				PageHelper.startPage(pageNum, 4);
				List<Complain> xq = articleService.getXq(articleId);
				PageInfo<Complain> info = new PageInfo<>(xq);
				model.addAttribute("info", info);
				model.addAttribute("xq", xq);
				return "article/tsxq";
			}
			
			
			@RequestMapping("cx")
			public String getcx(int c1,int c2) {
				articleService.cx(c1,c2);
				return "redirect:/article/tsxq";
			}
			
			
}
