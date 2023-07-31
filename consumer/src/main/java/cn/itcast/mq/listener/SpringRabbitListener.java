package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SpringRabbitListener {
    @RabbitListener(queues = "simple.queue")
    public void listenSpringRabbitMQ(String message) {
        System.out.println("message=" + message);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1(String message) {
        System.out.println("消费者1消费：" + message + "----" + LocalDateTime.now());
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String message) {
        System.err.println("消费者2消费：" + message + "----" + LocalDateTime.now());
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String message) {
        System.out.println("fanout.queue1接收到消息：" + message);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String message) {
        System.out.println("fanout.queue2接收到消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"blue", "red"}
    )
    )
    public void listenDirectQueue1(String message) {
        System.out.println("direct.queue1接收到消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"yellow", "red"}
    )
    )
    public void listenDirectQueue2(String message) {
        System.err.println("direct.queue2接收到消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String message) {
        System.out.println("topic.queue1接收到消息：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String message) {
        System.out.println("topic.queue2接收到消息：" + message);
    }

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(String message) {
        System.out.println("object.queue接收到消息：" + message);
    }
}
