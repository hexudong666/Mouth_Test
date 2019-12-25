package com.hexudong.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hexudong.entity.Article;
import com.hexudong.entity.Category;
import com.hexudong.entity.Channel;
import com.hexudong.entity.Comment;
import com.hexudong.entity.Complain;

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
	List<Article> listByUser(@Param("id")Integer id);

	
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
	int updateStatus(@Param("id")int id, @Param("status") int status);


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
	List<Category> getCategorisByCid(int cid);

	
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

	/**
	 * 
	 * @param id
	 * @return
	 */
	Article findById(int id);
	
	/*
	 * 修改
	 */
	@Update("UPDATE cms_article SET title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},"
			+ " category_id=#{categoryId},status=0,"
			+ "updated=now() WHERE id=#{id} ")
	int update(Article article);

	
	//获取文章的简要信息  常常用于判断文章的存在性
	@Select("SELECT id,title,channel_id channelId , category_id categoryId,status ,hot "
			+ " FROM cms_article WHERE id = #{value} ")
	Article getInfoById(int id);


	//设置热门/不热门
	@Update("UPDATE cms_article SET hot=#{hot} WHERE id=#{myid}")
	int setHot(@Param("myid") int id, @Param("hot") int status);


	// 修改数据
	@Update("UPDATE cms_article SET status=#{myStatus} WHERE id=#{myid}")
	int CheckStatus(@Param("myid") int id, @Param("myStatus") int status);


	/**
	 * 文章列表
	 * @param status  文章状态
	 */
	List<Article> list(int status);


	//评论
	@Insert("INSERT INTO cms_comment(articleId,userId,content,created)"
			+ " VALUES(#{articleId},#{userId},#{content},NOW())")
	int addComment(Comment comment);


	//增加一条评论
	@Update("UPDATE cms_article SET commentCnt=commentCnt+1 WHERE id=#{value}")
	int increaseCommentCnt(int id);

	
	//根据文章id获取对应的评论
	@Select("SELECT c.id,c.articleId,c.userId,u.username as userName,c.content,c.created FROM cms_comment as c "
			+ " LEFT JOIN cms_user as u ON u.id=c.userId "
			+ " WHERE articleId=#{value} ORDER BY c.created DESC")
	List<Comment> getComments(int articleId);


	
	
	//获取热门文章
	List<Article> hostList();

	//获取最新文章
	List<Article> lastList(int pageSize);


	//根据分类和栏目获取文章
	List<Article> getArticles(@Param("channelId")  int channleId, @Param("catId") int catId);


	//获取栏目下的分类
	@Select("SELECT id,name FROM cms_category where channel_id=#{value}")
	@ResultType(Category.class)
	List<Category> getCategoriesByChannelId(int channleId);

	//添加投诉
	@Insert("insert into cms_complains (article_id,user_id,complaintype,urlip,created) values (#{articleId},#{userId},#{compainOption},#{srcUrl},now())")
	int addCoplain(Complain complain);


	@Update("UPDATE cms_article SET complainCnt=complainCnt+1,status=if(complainCnt>50,2,status)  "
			+ " WHERE id=#{value}")
	void increaseComplainCnt(Integer articleId);


	List<Complain> getComplains(int articleId);


	
	//根据文章id查询每个文章 的投诉
	List<Complain> getWZComplains(int articleId);


	List<Complain> getXq(int articleId);


	void cx(int c1, Object $missing$);


	

}
