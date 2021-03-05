package com.ictway.mqtt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ictway.mqtt.service.SendMqttService;

//mqtt에 관련된 요청을 처리하는 controller
@Controller
public class MqttController {
	
	private static final Logger logger = LoggerFactory.getLogger(MqttController.class);
	
	//http로 receiver가 요청을 하였을때
	@RequestMapping(value = "/mqtt", method = RequestMethod.GET)
	public String receiveMessage(@RequestParam("id") String id,@RequestParam("gps") String gps,@RequestParam("time") String time,Model model) {
		//id,gps,시간정보를 얻듬
		logger.info("access mqtt");
		
		MqttDomain mqttDomain=new MqttDomain();
		mqttDomain.setResult("received");
		Gson gson=new Gson();
		SendMqttService sendMqttService=new SendMqttService(id,gson.toJson(mqttDomain));
		if(sendMqttService.sendMqtt(2))
		{
			mqttDomain.setResult("success");
			model.addAttribute("result","success");
		}else {
			mqttDomain.setResult("fail");
			model.addAttribute("result","fail");
		}
		return "mqtt";
	}

	private class MqttDomain {
		
		private String result;
		
		public String getResult() {
			return result;
		}
		
		public void setResult(String result) {
			this.result=result;
		}

	}
}
