package com.hexudong.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hexudong.entity.Article;
import com.hexudong.entity.Category;
import com.hexudong.entity.Channel;
import com.hexudong.entity.Comment;
import com.hexudong.entity.Complain;
import com.hexudong.entity.Slide;

public interface ArticleService {

	
	//根据用户列出文章
	PageInfo<Article> listByUser(Integer id, int page);
	
	//删除
	int delete(int id);

	//	//获取栏目
	List<Channel> getChannels();
	//根据频道  获取分类
	List<Category> getCategorisByCid(int cid);

	int add(Article article);
	
	//根据文章id获取文章对象
	Article getById(int id);

	int update(Article article, Integer id);

	//获取文章的简要信息  常常用于判断文章的存在性
	Article getInfoById(int id);

	//设置热门不热门
	int setHot(int id, int status);

	// 修改数据
	int setCheckStatus(int id, int status);

	//获取文章列表
	PageInfo<Article> list(int status, int page);

	
	//热门列表
	PageInfo<Article> hotList(int page);

	//获取最新文章
	List<Article> lastList();

	//轮播图
	List<Slide> getSlides();

	
	//获取栏目下的文章    
	PageInfo<Article> getArticles(int channelId, int catId, int page);

	
	//获取栏目下的分类   
	List<Category> getCategoriesByChannelId(int channelId);

	
	// 发表评论
	int addComment(Comment comment);

	//根据文章id获取评论
	PageInfo<Comment> getComments(int articleId, int page);
	
	//添加评论
	int addComplian( Complain complain);

	//获取投诉
	PageInfo<Complain> getComplains(int articleId, int page);

	
	//获取文章投诉
	PageInfo<Complain> getWZComplains(int articleId, int page);

	List<Complain> getXq(int articleId);

	void cx(int c1, int c2);

	

	

}
