package com.hexudong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.hexudong.eitity.Article;

public interface ArticleMapper {

	/**
	 * 
	    * @Title: listByUser
	    * @Description: 根据用户获取文章的列表
	    * @param @param id
	    * @param @return    参数
	    * @return List<Article>    返回类型
	    * @throws
	 */
	List<Article> listByUser(Integer id);

	
	/**
	 * 
	    * @Title: updateStatus
	    * @Description: 修改文章的状态    
	    * @param @param id
	    * @param @param articleStatusDel
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	@Update("UPDATE cms_article SET deleted=#{status} WHERE id=#{id}")
	int updateStatus(@Param("")int id, int articleStatusDel);
	
	
	

}
