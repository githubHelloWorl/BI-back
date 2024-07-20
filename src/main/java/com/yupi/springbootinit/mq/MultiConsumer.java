package com.yupi.springbootinit.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class MultiConsumer {

  private static final String TASK_QUEUE_NAME = "task_queue";

  public static void main(String[] argv) throws Exception {
      // 创建连接工厂
    ConnectionFactory factory = new ConnectionFactory();
      factory.setHost("121.41.56.218");
      factory.setPort(5672);
      factory.setUsername("rabbit");
      factory.setPassword("rabb1213");

    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();

    channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    // 控制单个消费者的处理积压消息数量
      // 每个消费者最多处理一个任务
    channel.basicQos(1);

    // 定义如何消费
    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
        System.out.println("得到的消息 : " + message);

        System.out.println(" [x] Received '" + message + "'");
        try {
            doWork(message);
        } finally {
            System.out.println(" [x] Done");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    };
    // 开启消费监听
      // 消费确认机制
      // 只有消费者接收消息后，给一个反馈: ack/nack/reject
      // 只有给予正确的反馈消息，rabbit才可以放心的移除消息
      // 支持 autoack, 一旦接收到消息, 则自动反馈 ack 消息
      // 建议将 autoack 改为 false, 这样手动确认, 返回 ack 消息
    channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
  }

  private static void doWork(String task) {
    for (char ch : task.toCharArray()) {
        if (ch == '.') {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException _ignored) {
                Thread.currentThread().interrupt();
            }
        }
    }
  }
}