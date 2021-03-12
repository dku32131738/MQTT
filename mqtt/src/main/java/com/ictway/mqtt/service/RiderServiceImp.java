package com.ictway.mqtt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.repository.RiderRepository;

@Service
public class RiderServiceImp implements RiderService {
	
	@Inject
	private RiderRepository riderRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(RiderRepository.class);

	@Override
	public List<RiderDomain> findAll() throws Exception {
		// TODO Auto-generated method stub
		return riderRepository.findAll();
	}

	@Override
	public Optional<RiderDomain> findById(String id) throws Exception {
		// TODO Auto-generated method stub
		HashMap map=new HashMap();
		map.put("id", id);
		return riderRepository.findById(map);
	}

	@Override
	public void insertRider(RiderDomain riderDomain) throws Exception {
		// TODO Auto-generated method stub
		riderRepository.insertRider(riderDomain);
	}

	@Override
	public void updateRider(RiderDomain riderDomain) throws Exception {
		// TODO Auto-generated method stub
		riderRepository.updateRider(riderDomain);
	}

}
