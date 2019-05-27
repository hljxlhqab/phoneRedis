package com.oracle.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.vo.User;

import redis.clients.jedis.Jedis;

public class Test {

	public static void main(String[] args) {
		Jedis edis=MyRedisPool.getPool().getResource();
		User user=new User("qab","tiger","13603642094");
		ObjectMapper mapper=new ObjectMapper();
		try {
			edis.set("qab", mapper.writeValueAsString(user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		edis.close();

		
		System.out.println("这是我的人呀；哈哈； ");
		
		System.out.println("我又改了");
		
		System.out.println("这次又改了吗？嗯？");
		
		
	}

}
