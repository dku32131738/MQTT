package mqtt;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.repository.PGRiderRepository;

public class TestDB {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(TestDB.class);
	
	@Test
	public void connect() {
		try {
		Connection conn = (Connection) sqlSession.getConnection();
		logger.info("성공 : "+conn );
		
		} catch (Exception ex){
			logger.error("연결실패");
			ex.printStackTrace();
		}
	}

}
