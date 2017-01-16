package com.test.module.test;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller("/control")
public class Controller {

	@ResponseBody
	@PatchMapping("/test1")
	public String test1(@RequestBody Integer a) {
		//你有宝气吧
		//你有宝气吧 2016年10月20日 下午9:38:49 Administrator,
		System.out.println(a);
		//你有宝气吧
		// url:http://test-basis-web:port/control/test1.do
		//http://test-basis-web:port//person/profile/11
		return "";
	}

}
