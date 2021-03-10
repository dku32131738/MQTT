package com.ictway.mqtt.service;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.repository.PGRiderRepository;

@Service
public class AccessDBService {
	
	@Autowired
	private PGRiderRepository pgRiderRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(AccessDBService.class);
	
	public boolean riderService(String id) {
		logger.info(pgRiderRepository.findById(id).get().getId());
		return true;
	}
	
	public RiderDomain clientService(String id) {
		RiderDomain rider;
		try {
			rider=pgRiderRepository.findById(id).get();
		}catch(NoSuchElementException e) {
			rider=null;
			logger.error(e.toString());
		}
		logger.info("라이더 찾기 완료");
		return rider;
	}
}
