package com.test.module.zookeeper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author beacor
 * 2016��12��16��	����1:46:39
 *
 */
public class ZookeeperTest {

	public static void main(String[] args) throws Exception {
		//��Ⱥ����
		String address="192.168.8.129:2181,192.168.8.130:2181";
		//���ӳ�ʱʱ��
		int outTime=5000;
		ZooKeeper zk=new ZooKeeper(address,outTime,new Watcher() {
			@Override
			public void process(WatchedEvent arg0) {
				System.out.println("�¼���"+arg0.getType());
				System.out.println("״̬��"+arg0.getState());
			}
		});
		
		//
		//ZooKeeper.States.CONNECTED
		//�ȴ�����
		while(!ZooKeeper.States.CONNECTED.equals(zk.getState()))
		{
			Thread.sleep(3000);
		}
		
		//System.out.println("{"+new String(zk.getData("/zk", true, null))+"}");
		Gson gs=new GsonBuilder().create();
		
		System.out.println(MessageFormat.format("���ڵ㣺nodeName:{0},value:{1}", "zk",new String(zk.getData("/zk", true, null))));
		StringBuilder config=new StringBuilder("{");
		List<String> childrens=zk.getChildren("/zk", true);
		int childrenSize=childrens.size();
		for (int i = 0; i < childrenSize; i++) {
			if(i>0){
				config.append(",");
			}
			config.append(new String(zk.getData("/zk/"+childrens.get(i), true, null)));
		}
		config.append("}");
		
		Properties properties=gs.fromJson(config.toString(), Properties.class);
		//System.out.println(config.toString());
		for (Object obj: properties.keySet()) {
			System.out.println(MessageFormat.format("{0} \t {1}", obj,properties.getProperty(obj.toString())));
		}
		//���ݽڵ��ȡ����
		/*List<String> childrens=zk.getChildren("/zk", true);
		for (String str : childrens) {
			System.out.println(new String(zk.getData("/zk/"+str, true, null)));
		}
		*/
		System.out.println("��������");
		zk.close();
	}
}
