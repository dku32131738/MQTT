package com.ictway.mqtt.dao;

import java.util.List;

import com.ictway.mqtt.dto.RiderDTO;

public interface RiderDAO {
	public List<RiderDTO> selectID();
	public int insertDB(RiderDTO riderDTO);
}
