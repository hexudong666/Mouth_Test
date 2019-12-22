package com.hexudong.common;

import org.apache.commons.codec.digest.DigestUtils;

public class CmsUtils {
/**
 * 
    * @Title: encty
    * @Description: 实体类
    * @param @param src   明文密码
    * @param @param salt   盐
    * @return String    返回类型
    * @throws
 */
	public static String  encry(String src,String salt) {
		return  DigestUtils.md5Hex(salt+src+salt);
	}
	
}
