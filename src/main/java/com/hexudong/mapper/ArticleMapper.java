package com.hexudong.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.hexudong.eitity.Article;
import com.hexudong.eitity.Category;
import com.hexudong.eitity.Channel;

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


	/**
	 * 
	    * @Title: getAllChannels
	    * @Description: //获取栏目
	    * @param @return    参数
	    * @return List<Channel>    返回类型
	    * @throws
	 */
	@Select("SELECT id,name FROM cms_channel")
	List<Channel> getAllChannels();


	/**
	 * 
	    * @Title: getCategorisByCid
	    * @Description: 根据频道  获取分类
	    * @param @param cid
	    * @param @return    参数
	    * @return List<Category>    返回类型
	    * @throws
	 */
	@Select("SELECT id,name FROM cms_category WHERE channel_id = #{value}")
	List<Category> getCategorisByCid(@Param("cid")int cid);

	
	/**
	 * 
	    * @Title: add
	    * @Description: 发布文章 
	    * @param @param article
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	@Insert("INSERT INTO cms_article(title,content,picture,channel_id,category_id,user_id,hits,hot,status,deleted,created,updated,commentCnt,articleType)"
			+ " VALUES(#{title},#{content},#{picture},#{channelId},#{categoryId},#{userId},0,0,0,0,now(),now(),0,#{articleType})")
	int add(Article article);

}
