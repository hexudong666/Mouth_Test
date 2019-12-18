package com.hexudong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.hexudong.eitity.Slide;

public interface SlideMapper {
	
	//管理轮播图
	@Select("SELECT id,title,picture,url FROM cms_slide ORDER BY id ")
	List<Slide> list();
	
}
