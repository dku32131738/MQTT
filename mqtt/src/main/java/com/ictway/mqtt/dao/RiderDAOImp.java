package com.ictway.mqtt.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ictway.mqtt.dto.RiderDTO;

@Repository
public class RiderDAOImp implements RiderDAO{

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<RiderDTO> selectID() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sql.sel");
	}

	@Override
	public int insertDB(RiderDTO riderDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("sql.insertRider",riderDTO);
	}

}
