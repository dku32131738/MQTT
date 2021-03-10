package com.ictway.mqtt.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.ictway.mqtt.domain.RiderDomain;

public interface RiderRepository {
	public List<RiderDomain> findAll();
	public Optional<RiderDomain> findById(@Param("id") String id);
	public void insertRider(RiderDomain riderDomain);
}
