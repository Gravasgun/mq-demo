package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    //交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("itcast.fanoutExchange");
    }

    //队列1
    @Bean
    public Queue queue1() {
        return new Queue("fanout.queue1");
    }

    //队列2
    @Bean
    public Queue queue2() {
        return new Queue("fanout.queue2");
    }

    //队列3
    @Bean
    public Queue ObjectQueue() {
        return new Queue("object.queue");
    }

    //绑定关系1
    @Bean
    public Binding fanoutBinding1(Queue queue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }

    //绑定关系2
    @Bean
    public Binding fanoutBinding2(Queue queue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }
}
