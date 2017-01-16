package com.test.module.zookeeper.server.service;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author beacor
 * 2016年12月19日	上午9:26:16
 *
 */
public class ZookeeperServerInitializing {
	//zk连接地址：[ip:端口]
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
		//等待连接
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
