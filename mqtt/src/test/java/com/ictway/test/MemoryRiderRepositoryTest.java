package com.ictway.test;

import java.util.Date;

import org.junit.After;
import org.junit.Test;

import com.ictway.mqtt.domain.Rider;
import com.ictway.mqtt.repository.MemoryRiderRepository;

public class MemoryRiderRepositoryTest {

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
	public void findById() {
		Date time = new Date();
		Rider rider1=new Rider();
		rider1.setId("rider1");
		rider1.setGps("127.0.01:136.06.3");
		rider1.setTime(time);
		repository.save(rider1);
		
		Rider rider2=new Rider();
		rider2.setId("rider1");
		rider2.setGps("127.0.01:136.06.3");
		rider2.setTime(time);
		repository.save(rider2);
		
		Rider result=repository.findById("rider1").get();
	}
}
