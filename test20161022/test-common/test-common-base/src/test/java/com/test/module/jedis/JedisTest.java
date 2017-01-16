package com.test.module.jedis;

import redis.clients.jedis.Jedis;

public class JedisTest {
	public static void main(String[] args) {
		//Á¬½Óredis
		Jedis  redis = new Jedis ("192.168.8.129");
		// ShardedJedisPool pool=new ShardedJedisPool(null,null);
		// ShardedJedis j=pool.getResource();
		// new GsonBuilder().create().fromJson("", Set.class);
		// j.set("", "");
		// j.close();
		
		//redis.set("age", "27");
		//redis.del("age");
		try {
			System.out.println("name:"+redis.get("name"));
			System.out.println(redis.exists("name"));
		} finally {
			redis.close();
		}
	}
}
