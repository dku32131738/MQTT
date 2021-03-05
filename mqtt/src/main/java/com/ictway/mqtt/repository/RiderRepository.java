package com.ictway.mqtt.repository;

import java.util.List;
import java.util.Optional;

import com.ictway.mqtt.domain.Rider;

public interface RiderRepository {
	
	Rider save(Rider rider);
	Optional<Rider> findById(String id);
	List<Rider> findAll();
	Rider updateGPS(String id,String newGPS,String newTime);
}
