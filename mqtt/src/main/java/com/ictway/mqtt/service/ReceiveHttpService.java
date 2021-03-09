package com.ictway.mqtt.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ictway.mqtt.dao.RiderDAOImp;
import com.ictway.mqtt.dto.RiderDTO;

@Service
public class ReceiveHttpService implements IReceiveHttpService{

	@Autowired
	RiderDAOImp riderDAO;
	
	@Override
	public int insertRider(String id, String gps, String time) {
		// TODO Auto-generated method stub
		Date rtime;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd HH:MI:SS");
		try {
			rtime=simpleDateFormat.parse(time);
		}catch(Exception e)
		{
			rtime=null;
			System.out.println(e.toString());
		}
		RiderDTO riderDTO=new RiderDTO();
		riderDTO.setId(id);
		riderDTO.setGps(gps);
		riderDTO.setTime(rtime);
		return riderDAO.insertDB(riderDTO);
	}
}
