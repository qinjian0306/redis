package com.pc.redis.controller;

import com.pc.redis.pubsub.PubClient;
import com.pc.redis.pubsub.PubSubListener;
import com.pc.redis.pubsub.SubClient;
import com.pc.redis.single.JedisCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private PubClient pubClient;
    @Autowired
    private SubClient subClient;
    @Autowired
    private PubSubListener pubSubListener;

    @RequestMapping("/pub")
    public String pub(){

        pubClient.publish("pubsub test" , "msg : ----->>>  redis pub test");

        return "pub";
    }

//    @RequestMapping("/sub")
//    public String sub(){
//
//        subClient.setChannelAndPubsub(pubSubListener,"test");
//        // 启动线程
//        subClient.start();
//
//        return "sub";
//    }

}
