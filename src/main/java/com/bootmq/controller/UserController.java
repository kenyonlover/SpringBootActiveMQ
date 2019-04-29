package com.bootmq.controller;

import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bootmq.mqserver.SenderServer;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SenderServer mqServer;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private Topic topic;

	@RequestMapping("/registerQueue")
	public String registerQueue(String name) {
		long startTime = System.currentTimeMillis();

		// 数据库的操作
		try {
			Thread.sleep(50);
			// 为了提高用户体验
			// 发短信，去调用别人的API
			// mqServer.send("发送短信*******");
			// Thread.sleep(1000);
			// 发邮件，qq发邮件的smtp
			JSONObject json = new JSONObject();
			json.put("type", "email");
			json.put("to", "307980463@qq.com");
			json.put("content", "恭喜你注册Queue成功,"+name);

			mqServer.send(queue, json.toJSONString());
			// Thread.sleep(1000);

		} catch (InterruptedException e) {
		}
		long endTime = System.currentTimeMillis();
		return "你注册Queue成功，用户名为：" + name + ",耗时：" + (endTime - startTime);
	}
	
	@RequestMapping("/registerTopic")
	public String registerTopic(String name) {
		long startTime = System.currentTimeMillis();

		// 数据库的操作
		try {
			Thread.sleep(50);
			// 为了提高用户体验
			// 发短信，去调用别人的API
			// mqServer.send("发送短信*******");
			// Thread.sleep(1000);
			// 发邮件，qq发邮件的smtp
			JSONObject json = new JSONObject();
			json.put("type", "email");
			json.put("to", "307980463@qq.com");
			json.put("content", "恭喜你注册Topic成功,"+name);

			mqServer.send(topic, json.toJSONString());
			// Thread.sleep(1000);

		} catch (InterruptedException e) {
		}
		long endTime = System.currentTimeMillis();
		return "你注册Topic成功，用户名为：" + name + ",耗时：" + (endTime - startTime);
	}
}
