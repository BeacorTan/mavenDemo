package com.test.module.zookeeper.server.service;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author beacor
 * 2016��12��19��	����9:26:16
 *
 */
public class ZookeeperServerInitializing {
	//zk���ӵ�ַ��[ip:�˿�]
	private String connectString;
	private Integer sessionTimeout;
	
	private ZooKeeper zk;
	
	public ZookeeperServerInitializing() {
		this.connectString=ZooKeeperPropertyPlaceholderConfigurer.getStringValue("connectString");
		this.sessionTimeout=ZooKeeperPropertyPlaceholderConfigurer.getIntValue("sessionTimeout");
	}
	
	public   ZooKeeper initZk() throws Exception {
		  zk=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				
			}
		});
		//ZooKeeper.States.CONNECTED
		//�ȴ�����
		while(!ZooKeeper.States.CONNECTED.equals(zk.getState()))
		{
			Thread.sleep(3000);
		}
		return zk;
	}
	
	public void close() throws InterruptedException {
		if(null!=zk){
			zk.close();
		}
	}
}
