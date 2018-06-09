package com.pc.redis.cluster;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 第一步: 读取配置文件中redis参数 创建对象
 */
@Component
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisClusterProperties {

    private String nodes;
    private int timeout;
    private int expire;
    private final ClusterPool clusterPool = new ClusterPool();

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public ClusterPool getClusterPool() {
        return clusterPool;
    }

    public static class ClusterPool {
        public int maxTotal;
        public int maxIdle;
        public int minIdle;
        public long maxWaitMillis;
        public boolean testWhileIdle;
        public boolean testOnBorrow;

        public int getMaxTotal() {
            return maxTotal;
        }

        public void setMaxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public long getMaxWaitMillis() {
            return maxWaitMillis;
        }

        public void setMaxWaitMillis(long maxWaitMillis) {
            this.maxWaitMillis = maxWaitMillis;
        }

        public boolean isTestWhileIdle() {
            return testWhileIdle;
        }

        public void setTestWhileIdle(boolean testWhileIdle) {
            this.testWhileIdle = testWhileIdle;
        }

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }

        public boolean isTestOnReturn() {
            return testOnReturn;
        }

        public void setTestOnReturn(boolean testOnReturn) {
            this.testOnReturn = testOnReturn;
        }

        public boolean testOnReturn;

    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
