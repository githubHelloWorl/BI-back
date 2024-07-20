package com.yupi.springbootinit.mq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class SingleProducer {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("121.41.56.218");
        factory.setPort(5672);
        factory.setUsername("rabbit");
        factory.setPassword("rabb1213");

        try (Connection connection = factory.newConnection();
             // 创建频道,类似于客户端(client) 连接,方便连接
             Channel channel = connection.createChannel()) {
            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 发送消息
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}