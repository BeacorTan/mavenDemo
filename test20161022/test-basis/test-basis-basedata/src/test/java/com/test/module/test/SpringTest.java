package com.test.module.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring.xml",
		//ģ��spring�ļ�·��
		//"/com/test/test/server/MATE-INF/spring.xml"
		})
//------------����������´��룬���м̳и���Ĳ����඼����ѭ�����ã�Ҳ���Բ��ӣ��ڲ�����ķ�����///�������񣬲μ���һ��ʵ��  
//����ǳ��ؼ���������������ע�����ã�������ƾͻ���ȫʧЧ��  
//@Transactional  
//�������������������ļ��е������������transactionManager = "transactionManager"����ͬʱ//ָ���Զ��ع���defaultRollback = true�������������������ݲŲ�����Ⱦ���ݿ⣡  
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
//-
//@Rollback(false)  //����ʹ����˷��������񲻻ع�,trueʱΪ�ع�
public class SpringTest  extends AbstractJUnit4SpringContextTests {

	@Test
	//@Rollback(false)  //����ʹ����˷��������񲻻ع�,trueʱΪ�ع�  
	public void testName()  {
	}
}
