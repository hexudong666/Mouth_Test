package com.hexudong.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hexudong.common.CmsContant;
import com.hexudong.eitity.Article;
import com.hexudong.mapper.ArticleMapper;
import com.hexudong.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper articleMapper;
	
	
	
	
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
	
}
