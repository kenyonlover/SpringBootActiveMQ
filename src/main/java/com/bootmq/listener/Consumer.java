package com.bootmq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

@Component
public class Consumer {

	@JmsListener(destination = "${queue}")
	public void receive(String msg) {
		System.out.println("queue监听器1收到msg:" + msg);

		JSONObject parseObject = JSONObject.parseObject(msg);
		String type = (String) parseObject.get("type");
		String to = (String) parseObject.get("to");
		String content = (String) parseObject.get("content");
		if ("email".equals(type)) {
			System.out.println("发送邮件到:"+to+",内容为:"+content);
		}
	}
}
