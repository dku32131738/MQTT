package com.ictway.mqtt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ictway.mqtt.domain.ResponseDomain;
import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.service.AccessDBService;

@RestController
public class ClientRequestController {
	
	@Autowired
	private AccessDBService accessDBService;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientRequestController.class);

	@GetMapping("/client")
	@ResponseBody
	public ResponseDomain responseClient(@RequestParam("clientId") String clientId,@RequestParam("riderId") String RiderId) {
		logger.info("ClientResponseClient");
		ResponseDomain responseDomain=new ResponseDomain();
		responseDomain.setId(clientId);
		logger.info("set ID");
		RiderDomain rider=accessDBService.clientService("riderId");
		if(rider==null) {
			responseDomain.setMessage("검색 실패");
			responseDomain.setError("rider가 존재하지 않습니다.");
		}
		else {
			responseDomain.setMessage(rider.getId()+" "+rider.getLongitude()+" "+rider.getLatitude()+" "+rider.getTime());
		}
		return responseDomain;
	}
}
