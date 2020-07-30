package com.wang.util;

import java.util.Random;
/**
 * 用于手机调用的短信生成是随机数工具类
 * @author Administrator
 *
 */
public class GetCode {
	/**
     * 生成随机的6位数，短信验证码
     * @return
     */
    public  static String getMsgCode() {
        int n = 6;
        StringBuilder code = new StringBuilder();
        Random ran = new Random();
        for (int i = 0; i < n; i++) {
            code.append(Integer.valueOf(ran.nextInt(10)).toString());
        }
        return code.toString();
    } 
}
