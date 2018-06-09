package com.pc.redis;


import com.pc.redis.pubsub.PubSubListener;
import com.pc.redis.pubsub.SubClient;
import org.springframework.stereotype.Component;

@Component
public class SpringLoader {

    private static SpringLoader instance;

    private static SubClient subClient;
    private static PubSubListener pubSubListener;

    public SpringLoader(){}

    public static SpringLoader getInstance(){
        if (instance == null){
            instance = new SpringLoader();
        }
        return instance;
    }

    public static SubClient getSubClient(){

        subClient = ApplicationContextProvider.getApplicationContext().getBean(SubClient.class);
        return subClient;

    }



    public static PubSubListener getPubSubListener(){

        pubSubListener = ApplicationContextProvider.getApplicationContext().getBean(PubSubListener.class);
        return pubSubListener;

    }

}
