package com.pc.redis.cluster;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.TreeSet;

/**
 * 封装redis
 */
public class JedisClusterCli {

    /**
     * JedisCluster
     */
    protected JedisCluster jedisCluster;

    /**
     * 设置JedisCluster
     */

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }


    /**
     * 新增不过期的数据
     */
    public boolean setNoExpire(String key, String value) {
        try {
        	String result = jedisCluster.set(key, value);
        	if (result.equals("OK")) {
        		return true;
        	}
            return false;
        } catch (Exception e) {
            return false;
		}
    }
    
    /**
     * 新增不过期的数据
     */
    public boolean setNoExpire(int db, String key, String value) {
        try {
            jedisCluster.select(db);
            String result = jedisCluster.set(key, value);
        	if (result.equals("OK")) {
        		return true;
        	}
            return false;
        } catch (Exception e) {
        	return false;
		}
    }
    
    /**
     * 设置带过期时间的数据
     */
    public boolean set(String key, String value, int expireSeconds) {
        try{
            String result = jedisCluster.setex(key, expireSeconds, value);
            if (result.equals("OK")) {
        		return true;
        	}
            return false;
        } catch (Exception e) {
        	return false;
		}
    }
    
    /**
     * 设置带过期时间的数据
     */
    public boolean set(int db, String key, String value, int expireSeconds) {
        try {
            jedisCluster.select(db);
            String result = jedisCluster.setex(key, expireSeconds, value);
            if (result.equals("OK")) {
        		return true;
        	}
            return false;
        } catch (Exception e) {
        	return false;
		}
    }

    /**
     * 读取
     */
    public String get(String key) {
        try {
            return jedisCluster.get(key);
        } catch (Exception e) {
        	return null;
		}
    }

    /**
     * 读取
     */
    public String get(int db, String key) {
        try{
            jedisCluster.select(db);
            return jedisCluster.get(key);
        } catch (Exception e) {
        	return null;
		}
    }

    /**
     * 拼接数据
     */
    public Long append(String key, String value) {
        try {
            return jedisCluster.append(key, value);
        } catch (Exception e) {
        	return null;
		}
    }

    /**
     * 拼接数据
     */
    public Long append(int db, String key, String value) {
        try{
            jedisCluster.select(db);
            return jedisCluster.append(key, value);
        } catch (Exception e) {
        	return null;
		}
    }


    /**
     * 删除
     */
    public Long delete(String... keys) {
        try{
            return jedisCluster.del(keys);
        } catch (Exception e) {
        	return 0L;
		}
    }
    
    /**
     * 删除
     */
    public Long delete(int db, String... keys) {
        try {
            jedisCluster.select(db);
            return jedisCluster.del(keys);
        } catch (Exception e) {
        	return 0L;
		}
    }
    
    /**
     * 设置到期时间
     */
    public Long expire(String key, int seconds) {
        try{
            return jedisCluster.expire(key, seconds);
        } catch (Exception e) {
        	return 0L;
		}
    }
    
    /**
     * 设置到期时间
     */
    public Long expire(int db, String key, int seconds) {
        try {
            jedisCluster.select(db);
            return jedisCluster.expire(key, seconds);
        } catch (Exception e) {
        	return 0L;
		}
    }


    /**
     * 获取一类key keys
     */
    public TreeSet<String> keys(String prefix) {
        TreeSet<String> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
        for (String k : clusterNodes.keySet()) {
            JedisPool jedisPool = clusterNodes.get(k);
            Jedis jedis = jedisPool.getResource();
            try {
                keys.addAll(jedis.keys(prefix+"*"));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();//用完一定要close这个链接！！！
            }
        }
        return keys;
    }
}
