package com.bootmq.mqserver;

import javax.jms.Destination;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class SenderServer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;
	
	/**
	 * 发送Queue消息
	 * @author Administrator
	 * @time 2019年4月29日 下午2:48:04
	 * @used 用途：
	 * @param msg
	 */
	public void send(String msg) {
		jmsMessagingTemplate.convertAndSend(queue, msg);
	}
	
	/**
	 * 通过参数dest判断是发送Queue消息还是Topic消息
	 * @author Administrator
	 * @time 2019年4月29日 下午2:48:18
	 * @used 用途：
	 * @param dest
	 * @param msg
	 */
	public void send(Destination dest, String msg) {
		jmsMessagingTemplate.convertAndSend(dest, msg);
	}
}
