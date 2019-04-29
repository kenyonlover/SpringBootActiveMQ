package com.bootmq.testController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.bootmq.mqserver.SenderServer;

/**
 * 作者：administrator
 * 时间：2019年4月29日 上午10:42:47
 * 说明：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJmsApplicationTests {
	
	@Autowired
	private SenderServer mqServer;
	
	@Test
	public void register() {
		for (int i = 0; i < 10; i++) {
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
				json.put("content", "恭喜你注册成功,"+"用户"+i);
				
				mqServer.send(json.toJSONString());
				// Thread.sleep(1000);
				
			} catch (InterruptedException e) {
			}
			long endTime = System.currentTimeMillis();
			System.out.println("你注册成功，用户名为：" + "用户"+i + ",耗时：" + (endTime - startTime)); 
		}
	}
}
