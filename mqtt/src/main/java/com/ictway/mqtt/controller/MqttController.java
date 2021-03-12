package com.ictway.mqtt.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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
import com.ictway.mqtt.domain.ResponseDomain;
import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.repository.PGRiderRepository;
import com.ictway.mqtt.service.RiderService;
import com.ictway.mqtt.service.SendMqttService;

//mqtt에 관련된 요청을 처리하는 controller
@RestController
public class MqttController {
	
	@Inject
	private RiderService riderService;
	
	private static final Logger logger = LoggerFactory.getLogger(MqttController.class);
	
	//http로 receiver가 요청을 하였을때
	@GetMapping("/rider")
	@ResponseBody
	public RiderDomain receiveMessage(@RequestParam String id,@RequestParam String latitude,@RequestParam String longitude,@RequestParam String time){
		//id,gps,시간정보를 얻듬
		logger.info("welcome to server");
		RiderDomain riderDomain=new RiderDomain();
		Gson gson=new Gson();
		ResponseDomain responseDomain=new ResponseDomain();
		responseDomain.setId(id);
		responseDomain.setMessage("received");
		riderDomain.setId(id);
		riderDomain.setLatitude(Float.parseFloat(latitude));
		riderDomain.setLongitude(Float.parseFloat(longitude));
		Date date;

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date=transFormat.parse(time);
		}catch(Exception e) {
			responseDomain.setError("시간 형식이 잘못되었습니다.");
			logger.error("시간 변환 실패 ,null로 변경됩니다.");
			date=null;
		}
		riderDomain.setTime(date);
		try {
			if(riderService.findById(id).orElse(null) != null) {
				riderService.updateRider(riderDomain);
				logger.info("DB updated");
			}else {
				riderService.insertRider(riderDomain);
				logger.info("DB inserted");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendMqttService sendMqttService=new SendMqttService(id,gson.toJson(responseDomain));
		sendMqttService.sendMqtt(2);
		return riderDomain;
	}
	
}