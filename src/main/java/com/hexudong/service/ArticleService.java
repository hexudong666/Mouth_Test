package com.hexudong.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hexudong.eitity.Article;
import com.hexudong.eitity.Category;
import com.hexudong.eitity.Channel;

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

	PageInfo<Article> list(int status, int page);
	

}
