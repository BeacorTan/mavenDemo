/**
 * 
 */
package com.test.module.zookeeper.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author beacor
 * 2016��12��16��	����3:59:32
 *
 */
public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements EnvironmentAware {
	
	private static Logger logger = Logger.getLogger(ZooKeeperPropertyPlaceholderConfigurer.class);
	
	private ConfigurableListableBeanFactory configurableListableBeanFactory;
	
	public ConfigurableListableBeanFactory getConfigurableListableBeanFactory() {
		return configurableListableBeanFactory;
	}
	
	/**
	 * ������Ϣ
	 */
	private static Map<String,Object> ctxConfigMap;
	
	/* (non-Javadoc)
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(Environment environment) {
		//Ŀǰûʲô��
		String globalPath=environment.getProperty("global.config.path");
		logger.info("global.config.path"+globalPath);
	}

	/* (non-Javadoc)
	 *�������ã��滻xml�е�${}ռλ��
	 */
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		this.configurableListableBeanFactory=beanFactoryToProcess;
		ctxConfigMap=new HashMap<String,Object>();
		Set<Object> keys=props.keySet();
		String propertiesKey=null;
		for (Object key : keys) {
			propertiesKey=(String) key;
			ctxConfigMap.put(propertiesKey, props.get(propertiesKey));
		}
		
		ZookeeperServerInitializing zkServer=new ZookeeperServerInitializing();
		try {
			String isZK=getStringValue("isZK");
			if("1".equals(isZK)){
				ZooKeeper zk=zkServer.initZk();
				String configPath=new StringBuilder("/zk/").append(ctxConfigMap.get("zkChildrenPath")).toString();
				List<String> childrens=zk.getChildren(configPath, true);
				// List<String> childrens=zk.getChildren("/zk", true);
				
				StringBuilder config=new StringBuilder("{");
				int childrenSize=childrens.size();
				for (int i = 0; i < childrenSize; i++) {
					if(i>0){
						config.append(",");
					}
					config.append(new String(zk.getData(configPath+"/"+childrens.get(i), true, null)));
				}
				config.append("}");
				
				//google jsonת��
				Gson gs=new GsonBuilder().create();
				Properties properties=gs.fromJson(config.toString(), Properties.class);
				props.putAll(properties);
				
			}else{
				logger.info("�߱��������ļ�");
			}
			//�滻ռλ��
			super.processProperties(beanFactoryToProcess, props);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			try {
				//�ر�zk���ͷ���Դ
				zkServer.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		
	}
	
	public void processProperties(Properties props){
		//�滻ռλ��
		super.processProperties(configurableListableBeanFactory, props);
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param key
	 * @return
	 */
	public static String getStringValue(String key){
		if(ctxConfigMap.containsKey(key)){
			return (String) ctxConfigMap.get(key);
		}
		return "";
	}
	
	/**
	 * ��ȡ������Ϣ
	 * @param key
	 * @return
	 */
	public static Integer getIntValue(String key){
		if(ctxConfigMap.containsKey(key)){
			//object����ֱ��ת��ΪInteger����
			return Integer.valueOf((String)ctxConfigMap.get(key));
		}
		return 0;
	}
}
