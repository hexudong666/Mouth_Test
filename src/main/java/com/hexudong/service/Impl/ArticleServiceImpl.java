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
	
	//根据文章id获取文章对象
	@Override
	public Article getById(int id) {
		// TODO Auto-generated method stub
		return articleMapper.getById(id);
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
	
	
	@Override
	public PageInfo<Article> list(int status, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, CmsContant.PAGE_SIZE);
		return new PageInfo<Article>(articleMapper.list(status));
	}
	
	
}
