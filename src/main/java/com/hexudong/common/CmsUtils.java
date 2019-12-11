package com.hexudong.common;

import java.io.UnsupportedEncodingException;
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
	public static String  encty(String src,String salt) {
		byte[] md5 = DigestUtils.md5(salt+src+salt);
		try {
			String endPwd = new String(md5,"UTF-8");
			return endPwd;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return salt+src+salt;
		}
				
	}
	
}
