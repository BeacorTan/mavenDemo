package com.test.module.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring.xml",
		//模块spring文件路径
		//"/com/test/test/server/MATE-INF/spring.xml"
		})
//------------如果加入以下代码，所有继承该类的测试类都会遵循该配置，也可以不加，在测试类的方法上///控制事务，参见下一个实例  
//这个非常关键，如果不加入这个注解配置，事务控制就会完全失效！  
//@Transactional  
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！  
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
//-
//@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
public class SpringTest  extends AbstractJUnit4SpringContextTests {

	@Test
	//@Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚  
	public void testName()  {
	}
}
