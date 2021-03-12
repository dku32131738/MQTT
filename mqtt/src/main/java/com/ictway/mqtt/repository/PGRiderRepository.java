package com.ictway.mqtt.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ictway.mqtt.domain.RiderDomain;

@Repository
public class PGRiderRepository implements RiderRepository{

	private static final String namespace="com.ictway.mqtt.sql";
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(PGRiderRepository.class);

	@Override
	public List<RiderDomain> findAll() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".selectAll");
	}

	@Override
	public Optional<RiderDomain> findById(Map id) throws Exception {
		// TODO Auto-generated method stub
		List<RiderDomain> list=sqlSession.selectList(namespace+".selectById",id);
		Optional<RiderDomain> riderDomain=Optional.ofNullable(list.get(0));
		logger.info("리포지토리 완료");
		return riderDomain;
	}

	@Override
	public void insertRider(RiderDomain riderDomain) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".insertRider", riderDomain);
	}

	@Override
	public void updateRider(RiderDomain riderDomain) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".updateRider",riderDomain);
		
	}

}
