package com.test.module.zookeeper;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author beacor
 * 2016��12��16��	����1:46:39
 *
 */
public class ZookeeperTest2 {
	public static String url="192.168.8.129:2181,192.168.8.130:2181";
	public static String root = "/zk" ;
	public static String child1 = "/zk/child1" ;
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper(url,3000,new Watcher(){
			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				System.out.println("�������¼���"+event.getType());
//				System.out.println("�¼�״̬��"+event.getState()) ;
			}
		});
		
		while(!"CONNECTED".equals(zk.getState().toString()))
		{
			Thread.sleep(3000);
		}
		List<String> childrens=zk.getChildren("/zk", true);
		for (String string : childrens) {
			System.out.println(string);
			System.out.println(new String(string));
		}
		if(zk.exists(root, true)==null)
		{
			zk.create(root, "root".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT) ;
		}
		if(zk.exists(child1, true)==null)
		{
			zk.create(child1, "child1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL) ;
		}
		
		String rootDataString = new String(zk.getData(root, true, null)) ;
		System.out.println(rootDataString);
		
		zk.setData(root, "rootUpdate1".getBytes(), -1) ;
		rootDataString = new String(zk.getData(root, true, null)) ;
		System.out.println(rootDataString);
		System.out.println(zk.getChildren(root, true));
		
		System.out.println("----------------------");
		System.out.println(new String(zk.getData(child1, false, null)));
		zk.exists(child1, false);
		zk.setData(child1, "child1Update1".getBytes(), -1) ;
		System.out.println(new String(zk.getData(child1, true, null)));
//		zk.setData(child1, "child1Update1".getBytes(), -1) ;
		
		zk.close();
	}
}