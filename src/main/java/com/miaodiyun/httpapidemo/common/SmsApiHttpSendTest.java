package com.miaodiyun.httpapidemo.common;


import com.miaodiyun.httpapidemo.common.Config;
import com.miaodiyun.httpapidemo.common.HttpUtil;

import java.net.URLEncoder;

/**
 * 短信API发�??
 * @author JiangPengFei
 * @version $Id: javaHttpNewApiDemo, v 0.1 2019/1/23 11:42 JiangPengFei Exp $$
 */
public class SmsApiHttpSendTest {

	/**
	 * 短信发�??(验证码�?�知，会员营�?)
	 * 接口文档地址：http://www.miaodiyun.com/doc/https_sms.html
	 */
	public void execute() throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
		sb.append("&to").append("=").append("18103605867");
		sb.append("&param").append("=").append(URLEncoder.encode("1234,2","UTF-8"));
		sb.append("&templateid").append("=").append("272251324");
//		sb.append("&smsContent").append("=").append( URLEncoder.encode("【秒�?科技】您的验证码�?123456，该验证�?5分钟内有效�?�请勿泄漏于他人�?","UTF-8"));
		String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
		String result = HttpUtil.post(Config.BASE_URL, body);
		System.out.println(result);
	}

	public static void main(String[] args) {
		SmsApiHttpSendTest am = new SmsApiHttpSendTest();
		try {
			//am.execute();
			send("13359718053","ab67","2");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void send(String number,String code,String minite) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("accountSid").append("=").append(Config.ACCOUNT_SID);
		sb.append("&to").append("=").append(number);
		String value=code+","+minite;
		sb.append("&param").append("=").append(URLEncoder.encode(value,"UTF-8"));
		sb.append("&templateid").append("=").append("272251324");
//		sb.append("&smsContent").append("=").append( URLEncoder.encode("【秒�?科技】您的验证码�?123456，该验证�?5分钟内有效�?�请勿泄漏于他人�?","UTF-8"));
		String body = sb.toString() + HttpUtil.createCommonParam(Config.ACCOUNT_SID, Config.AUTH_TOKEN);
		String result = HttpUtil.post(Config.BASE_URL, body);
		System.out.println(result);
	}
}
