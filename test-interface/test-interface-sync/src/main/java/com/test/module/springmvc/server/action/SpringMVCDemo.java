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
	 * 单个参数传递,无返参:post请求方式
	 * URL：/springMVCDemo/singleParam.do?id=11111
	 * @param id
	 */
	@RequestMapping(value="/singleParam",method=RequestMethod.POST)
	public void singleParam(String id){
		logger.info(id);
	}
	
	/**
	 * 单个参数传递,有返参
	 * URL：
	 * @param id
	 */
	@RequestMapping(value="/singleParam2",method=RequestMethod.POST)
	//返回注解
	@ResponseBody
	public String singleParam2(String id){
		logger.info(id);
		return id;
	}
	
	/**
	 * 请求实体
	 * @param user 
	 * @return
	 */
	@RequestMapping(value="/requestEntity",method=RequestMethod.POST)
	//返回注解
	@ResponseBody
	public User requestEntity(@RequestBody User user){
		logger.info(user.toString());
		return user;
	}
}
