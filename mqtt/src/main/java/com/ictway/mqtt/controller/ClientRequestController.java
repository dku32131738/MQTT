package com.ictway.mqtt.controller;

import java.io.IOException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ictway.mqtt.domain.ResponseDomain;
import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.service.FirebaseCloudMessageService;
import com.ictway.mqtt.service.RiderService;
import com.ictway.mqtt.service.SendMqttService;

@RestController
public class ClientRequestController {
	
	@Inject
	private RiderService riderService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientRequestController.class);

	@GetMapping("/client")
	@ResponseBody
	public RiderDomain responseClient(@RequestParam("clientId") String clientId,@RequestParam("riderId") String riderId) throws IOException {
		logger.info("ClientResponseClient");
		RiderDomain rider;
		try {
			rider = riderService.findById(riderId).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			rider=null;
			logger.warn("라이더가 존재하지 않습니다.");
		}
		SendMqttService sendMqttService=new SendMqttService(clientId,rider);
		sendMqttService.sendMqtt(2);
		FirebaseCloudMessageService firebaseCloudMessageService=new FirebaseCloudMessageService();
		firebaseCloudMessageService.sendMessageTo(clientId, "라이더 위치 확인","test" );
		return rider;
	}
}
