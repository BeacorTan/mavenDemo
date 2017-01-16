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
 * 2016年12月16日	下午3:59:32
 *
 */
public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements EnvironmentAware {
	
	private static Logger logger = Logger.getLogger(ZooKeeperPropertyPlaceholderConfigurer.class);
	
	private ConfigurableListableBeanFactory configurableListableBeanFactory;
	
	public ConfigurableListableBeanFactory getConfigurableListableBeanFactory() {
		return configurableListableBeanFactory;
	}
	
	/**
	 * 配置信息
	 */
	private static Map<String,Object> ctxConfigMap;
	
	/* (non-Javadoc)
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(Environment environment) {
		//目前没什么用
		String globalPath=environment.getProperty("global.config.path");
		logger.info("global.config.path"+globalPath);
	}

	/* (non-Javadoc)
	 *加载配置，替换xml中的${}占位符
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
				
				//google json转换
				Gson gs=new GsonBuilder().create();
				Properties properties=gs.fromJson(config.toString(), Properties.class);
				props.putAll(properties);
				
			}else{
				logger.info("走本地配置文件");
			}
			//替换占位符
			super.processProperties(beanFactoryToProcess, props);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			try {
				//关闭zk，释放资源
				zkServer.close();
			} catch (Exception e) {
				logger.error(e);
			}
		}
		
	}
	
	public void processProperties(Properties props){
		//替换占位符
		super.processProperties(configurableListableBeanFactory, props);
	}
	
	/**
	 * 获取配置信息
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
	 * 获取配置信息
	 * @param key
	 * @return
	 */
	public static Integer getIntValue(String key){
		if(ctxConfigMap.containsKey(key)){
			//object不能直接转换为Integer类型
			return Integer.valueOf((String)ctxConfigMap.get(key));
		}
		return 0;
	}
}
