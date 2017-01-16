package com.test.module.test;

import com.google.gson.Gson;

/**
 * @author beacor
 * 2016��9��27������8:41:46
 * jsonװ��
 */
public class GsonTest {
	
	@org.junit.Test
	public void testName()  {
		Gson gson=new Gson();
		Emp emp=new Emp(27,"beacor");
		System.out.println(gson.toJson(emp));
		Emp e=gson.fromJson("{\"age\":27,\"name\":\"beacor\"}", Emp.class);
		System.out.println(e.toString());
	}
}

class Emp{
	private int age;
	private String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Emp(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}
	public Emp() {
		super();
	}
	@Override
	public String toString() {
		return "Emp [age=" + age + ", name=" + name + "]";
	}
}
