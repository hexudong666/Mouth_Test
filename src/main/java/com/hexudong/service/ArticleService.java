package com.hexudong.service;

import com.github.pagehelper.PageInfo;
import com.hexudong.eitity.Article;

public interface ArticleService {

	PageInfo<Article> listByUser(Integer id, int page);

	int delete(int id);

}
