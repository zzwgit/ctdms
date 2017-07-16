package test.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.jxufe.ctdms.dao.NoticesTypeDao;
import com.jxufe.ctdms.service.NoticesService;

@ContextConfiguration({"classpath*:applicationContext.xml"})  
public class NoticesTest {
	@Resource NoticesTypeDao noticesTypeDao;  
	  
	@Test
	public void test() {
		System.out.println(noticesTypeDao.findAll());
	}

}
