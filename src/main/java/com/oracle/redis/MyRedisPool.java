package com.oracle.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MyRedisPool {
	
	//JedisPool:连接池的类；    DataSource;
	private static volatile JedisPool pool;
	
	/**
	 * 获得唯一的一个连接池
	 * @return
	 */
	public static JedisPool getPool() {
		//double check:
		if(pool==null) {
			synchronized(MyRedisPool.class) {
				if(pool==null) {
					JedisPoolConfig config=new JedisPoolConfig();
					config.setMaxTotal(15);
					config.setMaxIdle(3);
					config.setMaxWaitMillis(8000);
					config.setTestOnBorrow(true);
					pool=new JedisPool(config,"192.168.50.14",6379,5000,"tiger");
				}
			}
		}
		return pool;
	}
	
	
	public static void test() {
		System.out.println("活动连接："+pool.getNumActive()+"，空闲连接:"+pool.getNumIdle());
	}
	

}
