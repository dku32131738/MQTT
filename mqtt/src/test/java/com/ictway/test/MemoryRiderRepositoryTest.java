package com.ictway.test;

import java.util.Date;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ictway.mqtt.domain.Rider;
import com.ictway.mqtt.repository.MemoryRiderRepository;
import com.ictway.mqtt.service.ReceiveHttpService;

public class MemoryRiderRepositoryTest {

	@Autowired
	ReceiveHttpService receiveHttpService;
	
	MemoryRiderRepository repository=new MemoryRiderRepository();
	
	@After
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Rider rider=new Rider();
		Date time = new Date();
		rider.setId("rider1");
		rider.setGps("127.0.01:136.06.3");
		rider.setTime(time);
		repository.save(rider);
	}
	
	@Test
	public void insertRider() {
		receiveHttpService.insertRider("r02","[agagaagagaahaaha]" ,"2021-03-15 10:00:11" );
	}
}
