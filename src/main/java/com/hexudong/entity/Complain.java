package com.hexudong.entity;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.hexudong.cms.utils.entity.StringUtils;

/**
 * 
    * @ClassName: Complain
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author Administrator
    * @date 2019年12月25日
    *
 */
public class Complain {
	
	private Integer id               ;//自增编号
	
	@NotNull
	private Integer articleId       ;//文章id

	private Integer userId          ;//用户id,谁投诉的
	
	@NotNull
	private Integer complainType    ;//投诉类型
	
	@NotBlank
	private String compainOption   ;//投诉类型
	private String srcUrl          ;//地址
	
	@Email
	private String email            ;//证据url地址
	
	private Integer user_id            ;//用户id
	
	private String urlip            ;//证据url地址

	private Date created          ;//举报日期
	
	private User user;//用户

	
	
	public String getUrlip() {
		return urlip;
	}
	
	public void setUrlip(String urlip) {
		this.urlip = urlip;
	}
	
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCompainOption() {
		return compainOption;
	}

	public void setCompainOption(String compainOption) {
		this.compainOption = compainOption;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}



	public Integer getComplainType() {
		return complainType;
	}

	public void setComplainType(Integer complainType) {
		this.complainType = complainType;
	}

	@Override
	public String toString() {
		return "Complain [id=" + id + ", articleId=" + articleId + ", userId=" + userId + ", complainType="
				+ complainType + ", compainOption=" + compainOption + ", srcUrl=" + srcUrl + ", email=" + email
				+ ", user_id=" + user_id + ", urlip=" + urlip + ", created=" + created + ", user=" + user + "]";
	}


}
