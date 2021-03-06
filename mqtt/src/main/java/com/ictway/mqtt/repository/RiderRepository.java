package com.ictway.mqtt.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.Keymap;

import org.apache.ibatis.annotations.Param;

import com.ictway.mqtt.domain.RiderDomain;

public interface RiderRepository {
	public List<RiderDomain> findAll() throws Exception;
	public Optional<RiderDomain> findById(Map id) throws Exception;
	public void insertRider(RiderDomain riderDomain) throws Exception;
	public void updateRider(RiderDomain riderDomain) throws Exception;
}
