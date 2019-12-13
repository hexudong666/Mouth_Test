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
	

}
