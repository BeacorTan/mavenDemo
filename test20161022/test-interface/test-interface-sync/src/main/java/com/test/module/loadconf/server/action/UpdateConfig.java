package com.test.module.loadconf.server.action;

import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.module.zookeeper.server.service.ZooKeeperPropertyPlaceholderConfigurer;

@Controller
@RequestMapping("loadconf")
//public class UpdateConfig extends PropertyPlaceholderConfigurer{
	public class UpdateConfig {
	
	private static Logger logger = Logger.getLogger(UpdateConfig.class);
	
	@Resource
	private ZooKeeperPropertyPlaceholderConfigurer zooKeeperPropertyPlaceholderConfigurer;
	
	@Resource
	private ITestConfig testConfig;
	
	@RequestMapping(value="/updateConf",method=RequestMethod.POST)
	@ResponseBody
	public String updateConf(@RequestParam("abc") String abc){
		Properties props=new Properties();
		props.put("connectString", "192.168.8.129:2181,192.168.8.130:2181");
		props.put("zkChildrenPath", "normal/TEST");
		props.put("parentNode", "/zk");
		props.put("isZK", "0");
		props.put("sessionTimeout", "5000");
		props.put("jdbc.driverClassName", "com.mysql.jdbc.Driver");
		props.put("jdbc.url", "jdbc:mysql://192.168.8.129:3306/test?useUnicode=true&allowMultiQueries=true");
		props.put("jdbc.username", "root");
		props.put("jdbc.password", "root");
		props.put("jdbc.testURL", "5000");
		//Ìæ»»Õ¼Î»·û
		zooKeeperPropertyPlaceholderConfigurer.processProperties(props);
		return abc;
	}
	
	
	@RequestMapping(value="/testValue",method=RequestMethod.POST)
	@ResponseBody
	public String testValue(){
		String url=testConfig.getUrl();
		logger.info("testConfig URL:"+url);
		
		return url;
	}
}
