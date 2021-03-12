package mqtt;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ictway.mqtt.domain.RiderDomain;
import com.ictway.mqtt.repository.PGRiderRepository;
import com.ictway.mqtt.repository.RiderRepository;
import com.ictway.mqtt.service.RiderService;

public class TestPGRiderRepository{
	
	@Inject
	private RiderService riderService;
	
	private static final Logger logger = LoggerFactory.getLogger(TestDB.class);
	
	@Test
	public void findAll() {
		try {
			List<RiderDomain> list=riderService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		try {
			riderService.findById("r01");
			logger.info("성공");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateRider() {
		RiderDomain riderDomain=new RiderDomain();
		riderDomain.setId("r01");
		riderDomain.setLatitude((float) 126.975036621094);
		riderDomain.setLongitude((float) 38.578421);
		riderDomain.setTime(new Date());
		try {
			riderService.updateRider(riderDomain);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
