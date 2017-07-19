package test.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.jxufe.ctdms.dao.NoticesTypeDao;
@Transactional  
@RunWith(SpringJUnit4ClassRunner.class)   
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })  
@ContextConfiguration({"classpath*:applicationContext.xml"})  
public class NoticesTest extends AbstractJUnit4SpringContextTests{
	@Autowired
	NoticesTypeDao noticesTypeDao;  
	  /*
	@Test
	public void test() {
		System.out.println(noticesTypeDao.findAll());
	}*/
	
	@Test
    public void test2() {
        //创建数据库成功
        EntityManagerFactory factory =Persistence.createEntityManagerFactory("smdjpatest");

    }

}
