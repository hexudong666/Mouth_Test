package com.hexudong.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.common.CmsContant;
import com.hexudong.eitity.Article;
import com.hexudong.eitity.Category;
import com.hexudong.eitity.Channel;
import com.hexudong.mapper.ArticleMapper;
import com.hexudong.service.ArticleService;



@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private  ArticleMapper articleMapper;
	
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
}
