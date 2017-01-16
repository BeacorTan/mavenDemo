package com.test.module.zookeeper;

import java.text.MessageFormat;
import java.util.Properties;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author beacor
 * 2016��12��16��	����1:46:39
 *
 */
public class PropertiesJsonTest {
	public static void main(String[] args)  {
		//��ֵ��json
		String str="{\"key1\":\"key1Value1\",\"key2\":\"key2Value3\",\"key3\":\"key3Value3\"}";
		
		//google jsonת��
		Gson gs=new GsonBuilder().create();
		Properties properties=gs.fromJson(str, Properties.class);
		
		for (Object obj: properties.keySet()) {
			System.out.println(MessageFormat.format("key:{0},value:{1}", obj,properties.getProperty(obj.toString())));
		}
	}
}
