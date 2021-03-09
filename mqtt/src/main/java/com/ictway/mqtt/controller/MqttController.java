package com.ictway.mqtt.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ictway.mqtt.dao.RiderDAOImp;
import com.ictway.mqtt.dto.RiderDTO;
import com.ictway.mqtt.service.ReceiveHttpService;
import com.ictway.mqtt.service.SendMqttService;

//mqtt에 관련된 요청을 처리하는 controller
@RestController
public class MqttController {
	
	private static final Logger logger = LoggerFactory.getLogger(MqttController.class);
	
	//http로 receiver가 요청을 하였을때
	@GetMapping("/mqtt")
	@ResponseBody
	public String receiveMessage(@RequestParam("id") String id,@RequestParam("gps") String gps,@RequestParam("time") String time) {
		//id,gps,시간정보를 얻듬
		logger.info("access mqtt");
		
		MqttDomain mqttDomain=new MqttDomain();
		Gson gson=new Gson();
		SendMqttService sendMqttService=new SendMqttService(id,gson.toJson(mqttDomain));
		if(sendMqttService.sendMqtt(2))
		{
			mqttDomain.setId(id);
			mqttDomain.setGps(gps);
			mqttDomain.setTime(new Date());
		}else {
		}
		return "mqtt";
	}
	
	private class MqttDomain {
		
		private String id;
		private String gps;
		private Date time;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getGps() {
			return gps;
		}
		public void setGps(String gps) {
			this.gps = gps;
		}
		public Date getTime() {
			return time;
		}
		public void setTime(Date time) {
			this.time = time;
		}

	}
}
