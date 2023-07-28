package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class SpringRabbitListener {
//    @RabbitListener(queues = "simple.queue")
//    public void listenSpringRabbitMQ(String message) {
//        System.out.println("message=" + message);
//    }

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
}
