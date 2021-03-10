package mqtt;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.repository.RiderRepository;

public class TestPGRiderRepository{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private static final Logger logger = LoggerFactory.getLogger(TestDB.class);

	@Test
	public void findById() {
		// TODO Auto-generated method stub
		RiderDomain riderDomain=new RiderDomain();
		List<Object> obj=sqlSession.selectList("sql.selectId","r01");
		logger.info(Integer.valueOf(obj.size()).toString());
	}
}
