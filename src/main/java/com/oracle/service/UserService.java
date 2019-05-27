package com.oracle.service;

import java.io.IOException;
import java.util.Random;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miaodiyun.httpapidemo.common.SmsApiHttpSendTest;
import com.oracle.redis.MyRedisPool;
import com.oracle.vo.User;

import redis.clients.jedis.Jedis;

public class UserService {

	String[] codes= {"1","2","3","4","5","6","7","8","9","0"};
	
	public User getUser(String userName) {
		Jedis edis=MyRedisPool.getPool().getResource();
		
		String result=edis.get(userName);
		if(result!=null) {
			ObjectMapper mapper=new ObjectMapper();
			try {
				return mapper.readValue(result, User.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		edis.close();
		return null;
	}
	
	//
	public void send(String phone) {
		//1.产生随机码； 		
		Jedis edis=MyRedisPool.getPool().getResource();
		Random r=new Random();
		String code=codes[r.nextInt(codes.length)]+codes[r.nextInt(codes.length)]+codes[r.nextInt(codes.length)]+codes[r.nextInt(codes.length)];
		edis.setex(phone, 120, code);
		try {
			SmsApiHttpSendTest.send(phone, code, "2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		edis.close();
	}
	
	
	public boolean isCode(String phone,String code) {
		Jedis edis=MyRedisPool.getPool().getResource();
		
		String c=edis.get(phone);
		edis.close();
		if(code.equals(c)) {
			return true;
		}
		
		return false;
	}
	
	public void resetPasswd(String userName,String password) {
		Jedis edis=MyRedisPool.getPool().getResource();
		ObjectMapper mapper=new ObjectMapper();
		
		User user=this.getUser(userName);
		user.setPassword(password);
		
		try {
			edis.set(userName, mapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		edis.close();
	}
	
}
