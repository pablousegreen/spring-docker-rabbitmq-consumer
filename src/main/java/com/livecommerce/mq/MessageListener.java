package com.livecommerce.mq;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Slf4j
@Component
public class MessageListener {
    Logger logger = getLogger(MessageListener.class);

    @RabbitListener(queues = MQconfig.QUEUE)
    public void listener(CustomMessage message){
        logger.info("Got Message {} message {} "+MQconfig.QUEUE + message);
    }
}
