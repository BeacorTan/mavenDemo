package com.test.module.springmvc.server.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.module.springmvc.server.shared.domain.User;

@Controller
@RequestMapping("springMVCDemo")
public class SpringMVCDemo {

	private static Logger logger = Logger.getLogger(SpringMVCDemo.class);
	
	
	/**
	 * ������������,�޷���:post����ʽ
	 * URL��/springMVCDemo/singleParam.do?id=11111
	 * @param id
	 */
	@RequestMapping(value="/singleParam",method=RequestMethod.POST)
	public void singleParam(String id){
		logger.info(id);
	}
	
	/**
	 * ������������,�з���
	 * URL��
	 * @param id
	 */
	@RequestMapping(value="/singleParam2",method=RequestMethod.POST)
	//����ע��
	@ResponseBody
	public String singleParam2(String id){
		logger.info(id);
		return id;
	}
	
	/**
	 * ����ʵ��
	 * @param user 
	 * @return
	 */
	@RequestMapping(value="/requestEntity",method=RequestMethod.POST)
	//����ע��
	@ResponseBody
	public User requestEntity(@RequestBody User user){
		logger.info(user.toString());
		return user;
	}
}
