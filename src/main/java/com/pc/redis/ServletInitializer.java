package com.pc.redis;


import com.pc.redis.pubsub.SubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class ServletInitializer {


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            String[] beans = ctx.getBeanDefinitionNames();
            for (String bean : beans) {
                System.out.println(bean);
            }


            initRedisSub();

        };


    }


    /** 启动 redis 订阅线程 **/
    private void initRedisSub() {

        SubClient subClient = SpringLoader.getSubClient();
        subClient.setChannelAndPubsub(SpringLoader.getPubSubListener(), "pubsub test");
        subClient.start();

    }


}
