package com.hexudong.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.common.CmsContant;
import com.hexudong.entity.Article;
import com.hexudong.entity.Category;
import com.hexudong.entity.Channel;
import com.hexudong.entity.Comment;
import com.hexudong.entity.Complain;
import com.hexudong.entity.Slide;
import com.hexudong.mapper.ArticleMapper;
import com.hexudong.mapper.SlideMapper;
import com.hexudong.service.ArticleService;



@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private  ArticleMapper articleMapper;
	
	@Autowired
	private SlideMapper slideMapper;
	
	//列表
	@Override
	public PageInfo<Article> listByUser(Integer id, int page) {
		//分页
		PageHelper.startPage(page, CmsContant.PAGE_SIZE);
		
		PageInfo<Article> articlePage = new PageInfo<Article>(articleMapper.listByUser(id));
		
		return articlePage;
	}
	
	//删除文章
	@Override
	public int delete(int id) {
		return articleMapper.updateStatus(id,CmsContant.ARTICLE_STATUS_DEL);
	}
	
	
	//获取栏目
	@Override
	public List<Channel> getChannels() {
		//获取栏目
		return articleMapper.getAllChannels();
	}
	
	
	//根据频道  获取分类
	@Override
	public List<Category> getCategorisByCid(int cid) {
		return articleMapper.getCategorisByCid(cid);
	}
	
	//添加图片
	@Override
	public int add(Article article) {
		return articleMapper.add(article);
	}
	
	//根据文章id获取文章对象
	@Override
	public Article getById(int id) {
		// TODO Auto-generated method stub
		return articleMapper.findById(id);
	}
	
	
	
	@Override
	public int update(Article article, Integer userId) {
		// TODO Auto-generated method stub
		Article articleSrc = this.getById(article.getId());
		if(articleSrc.getUserId() != userId) {
			// todo 如果  不是自己的文章 就不查
		}
		return articleMapper.update(article);
	}

	//获取全部文章
	@Override
	public PageInfo<Article> list(int status, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, CmsContant.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.list(status));
	}

	
	//获取文章的简要信息  常常用于判断文章的存在性
	@Override
	public Article getInfoById(int id) {
		return articleMapper.getInfoById(id);
	}
	
	
	//设置热门/不热门
	@Override
	public int setHot(int id, int status) {
		return articleMapper.setHot(id,status);
	}
	
	// 修改数据
	@Override
	public int setCheckStatus(int id, int status) {
		// TODO Auto-generated method stub
		return articleMapper.CheckStatus(id,status);
	}
	
	//获取文章列表
	
	
	//添加评论
	@Override
	public int addComment(Comment comment) {
		
		int result =  articleMapper.addComment(comment);
		 //文章评论数目自增
		if(result>0)
			articleMapper.increaseCommentCnt(comment.getArticleId());
		return result;
	}
	
	
	//获取评论
	@Override
	public PageInfo<Comment> getComments(int articleId, int page) {
		PageHelper.startPage(page, CmsContant.PAGE_SIZE);
		return new PageInfo<Comment>(articleMapper.getComments(articleId));
	}
	
	//获取热门文章
	@Override
	public PageInfo<Article> hotList(int page) {
		PageHelper.startPage(page,CmsContant.PAGE_SIZE);
		return new PageInfo<>(articleMapper.hostList());
	}
	
	//获取最新文章
	@Override
	public List<Article> lastList() {
		return articleMapper.lastList(CmsContant.PAGE_SIZE);
	}
	
	
	//轮播图
	@Override
	public List<Slide> getSlides() {
		return slideMapper.list();
	}
	
	//获取栏目下的文章     主页左面
	@Override
	public PageInfo<Article> getArticles(int channleId, int catId, int page) {
		PageHelper.startPage(page,CmsContant.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.getArticles(channleId, catId));
	}
	
	
	//栏目下的分类
	@Override
	public List<Category> getCategoriesByChannelId(int channleId) {
		return articleMapper.getCategoriesByChannelId(channleId);
	}

	@Override
	public int addComplian(Complain complain) {
		//添加投诉到数据库
		int result = articleMapper.addCoplain(complain);
		// 增加投诉的数量
		if(result>0)
			articleMapper.increaseComplainCnt(complain.getArticleId());
		return 0;
	}

	//
	@Override
	public PageInfo<Complain> getComplains(int articleId, int page) {
		PageHelper.startPage(page, CmsContant.PAGE_SIZE);
		return new PageInfo<Complain>(articleMapper.getComplains(articleId));
	}

	//单个文章的投诉详情
	@Override
	public PageInfo<Complain> getWZComplains(int articleId, int page) {
		PageHelper.startPage(page, 4);
		return new PageInfo<Complain>(articleMapper.getWZComplains(articleId));
	}
	
	@Override
	public List<Complain> getXq(int articleId) {
		return articleMapper.getXq(articleId);
	}
	
	
	@Override
	public void cx(int c1, int c2) {
		articleMapper.cx(c1,c2);
	}
	
}

