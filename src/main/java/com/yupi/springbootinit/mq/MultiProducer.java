package com.yupi.springbootinit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MultiProducer {

  private static final String TASK_QUEUE_NAME = "task_queue";

  private static final String[] TASK_ARGV = {"name", "hello", "world", "name", "hello", "world"};

  public static void main(String[] argv) throws Exception {
      // 创建连接工厂
    ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("121.41.56.218");
      factory.setPort(5672);
      factory.setUsername("rabbit");
      factory.setPassword("rabb1213");

    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        String message = String.join(".", TASK_ARGV);

        channel.basicPublish("", TASK_QUEUE_NAME,
                MessageProperties.PERSISTENT_TEXT_PLAIN,
                message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");
    }
  }
}