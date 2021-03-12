package com.ictway.mqtt.service;

import java.util.List;
import java.util.Optional;

import com.ictway.mqtt.domain.RiderDomain;

public interface RiderService {
	
	public List<RiderDomain> findAll() throws Exception;
	public Optional<RiderDomain> findById(String id) throws Exception;
	public void insertRider(RiderDomain riderDomain) throws Exception;
	public void updateRider(RiderDomain riderDomain) throws Exception;
}
