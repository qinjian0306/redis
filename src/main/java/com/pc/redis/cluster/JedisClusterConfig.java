package com.pc.redis.cluster;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 第三步: 创建jedisPool和jedisPoolConfig对象 并创建自己封装的JedisCli对象 用的时候只需要注入即可使用
 */
//@Configuration
//@EnableConfigurationProperties(RedisClusterProperties.class)
public class JedisClusterConfig {

    @Bean
    public JedisClusterPoolConfig jedisClusterPoolConfig(RedisClusterProperties redisClusterProperties) {
        JedisClusterPoolConfig jedisClusterPoolConfig = new JedisClusterPoolConfig();
        jedisClusterPoolConfig.setMaxTotal(redisClusterProperties.getClusterPool().getMaxTotal());
        jedisClusterPoolConfig.setMaxIdle(redisClusterProperties.getClusterPool().getMaxIdle());
        jedisClusterPoolConfig.setMinIdle(redisClusterProperties.getClusterPool().getMinIdle());
        jedisClusterPoolConfig.setMaxWaitMillis(redisClusterProperties.getClusterPool().getMaxWaitMillis());
        jedisClusterPoolConfig.setTestWhileIdle(redisClusterProperties.getClusterPool().isTestWhileIdle());
        jedisClusterPoolConfig.setTestOnBorrow(redisClusterProperties.getClusterPool().isTestOnBorrow());
        jedisClusterPoolConfig.setTestOnReturn(redisClusterProperties.getClusterPool().isTestOnReturn());
        return jedisClusterPoolConfig;
    }


    @Bean
    public JedisCluster jedisCluster(RedisClusterProperties redisClusterProperties, JedisClusterPoolConfig jedisClusterPoolConfig) {
        if (redisClusterProperties == null){
            return null;
        }
        String [] nodeArray = redisClusterProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        for (String ipPort :nodeArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes,redisClusterProperties.getTimeout(),jedisClusterPoolConfig);

    }

    /**
     * 封装的jedis对象
     * @param jedisCluster
     * @return
     */
    @Bean
    public JedisClusterCli jedisClusterCli(JedisCluster jedisCluster) {
        JedisClusterCli jedisClusterCli = new JedisClusterCli();
        jedisClusterCli.setJedisCluster(jedisCluster);
        return jedisClusterCli;
    }

}
