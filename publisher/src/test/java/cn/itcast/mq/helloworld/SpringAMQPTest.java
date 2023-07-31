package cn.itcast.mq.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringAMQPTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testAmqp() {
        String queueName = "simple.queue";
        String message = "hello SpringAmqp22";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello SpringAmqp--";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testFanoutExchange() {
        String exchangeName = "itcast.fanoutExchange";
        String message = "hello everyone";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testDirectExchange() {
        String exchangeName = "itcast.direct";
        String message = "hello red";
        rabbitTemplate.convertAndSend(exchangeName, "red", message);
    }

    //    @Test
//    public void testTopicExchange() {
//        String exchangeName = "itcast.topic";
//        String message = "hello china's everyone";
//        rabbitTemplate.convertAndSend(exchangeName, "china.people", message);
//    }
    @Test
    public void testTopicExchange() {
        String exchangeName = "itcast.topic";
        String message = "good weather";
        rabbitTemplate.convertAndSend(exchangeName, "Japan.news", message);
    }

    @Test
    public void testObjectQueue() {
        Map<String, Object> message = new HashMap<>();
        message.put("name", "刘航吉");
        message.put("age", 21);
        rabbitTemplate.convertAndSend("object.queue", message);
    }
}
