package com.ictway.mqtt.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ictway.mqtt.domain.RiderDomain;

@Repository
public class PGRiderRepository implements RiderRepository{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<RiderDomain> findAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sql.selectAll");
	}

	@Override
	public Optional<RiderDomain> findById(@Param("id") String id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable((RiderDomain)sqlSession.selectList("sql.selectId",id));
	}

	@Override
	public void insertRider(RiderDomain riderDomain) {
		// TODO Auto-generated method stub
		sqlSession.insert("sql.insertRider", riderDomain);
	}

}
