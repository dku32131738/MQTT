package com.ictway.mqtt.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ictway.mqtt.domain.Rider;

public class MemoryRiderRepository implements RiderRepository{

	private static Map<Long,Rider> store=new HashMap<>();
	private static long sequence=0L;
	@Override
	public Rider save(Rider rider) {
		rider.setPk(++sequence);
		store.put(rider.getPk(),rider);
		// TODO Auto-generated method stub
		return rider;
	}

	@Override
	public Optional<Rider> findById(String id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public List<Rider> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(store.values());
	}

	@Override
	public Rider updateGPS(String id, String newGPS, String newTime) {
		// TODO Auto-generated method stub
		return null;
	}

	public void clearStore() {
		store.clear();
	}
}
