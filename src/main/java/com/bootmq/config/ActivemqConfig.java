package com.bootmq.config;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

@Configuration
public class ActivemqConfig {
	@Value("${queue}")
	private String queue;
	
	@Value("${topic}")
	private String topic;

	@Bean
	public Queue wucyQueue() {
		return new ActiveMQQueue(queue);
	}
	
	@Bean
	public Topic wucyTopic() {
		return new ActiveMQTopic(topic);
	}
	
	/**
	 * JmsListener注解默认只接收queue消息,如果要接收topic消息,需要设置containerFactory
	 */
	@Bean
    public JmsListenerContainerFactory<?> topicListenerContainer(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory topicListenerContainer = new DefaultJmsListenerContainerFactory();
        topicListenerContainer.setPubSubDomain(true);
        topicListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        return topicListenerContainer;
    }
}