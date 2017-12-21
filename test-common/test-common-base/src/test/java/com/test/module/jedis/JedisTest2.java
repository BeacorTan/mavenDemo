package com.test.module.jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * jedis����
 * @author beacor
 * 2016��12��19��	����3:25:54
 *
 */
public class JedisTest2 {
	
	private Logger logger=Logger.getLogger(JedisTest2.class);
			
	@Resource  
    private ShardedJedisPool shardedJedisPool;  
  
  
    /** 
     * ����һ��key�Ĺ���ʱ�䣨��λ���룩 
     * @param key keyֵ 
     * @param seconds ���������� 
     * @return 1�������˹���ʱ��  0��û�����ù���ʱ��/�������ù���ʱ�� 
     */  
    public long expire(String key, int seconds) {  
        if (key==null || key.equals("")) {  
            return 0;  
        }  
        
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.expire(key, seconds);  
        } catch (Exception ex) {  
            logger.error("EXPIRE error[key=" + key + " seconds=" + seconds + "]" + ex.getMessage(), ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();
            //returnResource(shardedJedis);  
        }  
        return 0;  
    }  
  
    /** 
     * ����һ��key��ĳ��ʱ������ 
     * @param key keyֵ 
     * @param unixTimestamp unixʱ�������1970-01-01 00:00:00��ʼ�����ڵ����� 
     * @return 1�������˹���ʱ��  0��û�����ù���ʱ��/�������ù���ʱ�� 
     */  
    public long expireAt(String key, int unixTimestamp) {  
        if (key==null || key.equals("")) {  
            return 0;  
        }  
  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.expireAt(key, unixTimestamp);  
        } catch (Exception ex) {  
            logger.error("EXPIRE error[key=" + key + " unixTimestamp=" + unixTimestamp + "]" + ex.getMessage(), ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();
            //returnResource(shardedJedis);  
        }  
        return 0;  
    }  
  
    /** 
     * �ض�һ��List 
     * @param key �б�key 
     * @param start ��ʼλ�� ��0��ʼ 
     * @param end ����λ�� 
     * @return ״̬�� 
     */  
    public String trimList(String key, long start, long end) {  
        if (key == null || key.equals("")) {  
            return "-";  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.ltrim(key, start, end);  
        } catch (Exception ex) {  
            logger.error("LTRIM ����[key=" + key + " start=" + start + " end=" + end + "]" + ex.getMessage() , ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return "-";  
    }  
    
    /** 
     * ���Set���� 
     * @param key 
     * @return 
     */  
    public long countSet(String key){  
        if(key == null ){  
            return 0;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.scard(key);  
        } catch (Exception ex) {  
            logger.error("countSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {
        	shardedJedis.close();
           // returnResource(shardedJedis);  
        }  
        return 0;  
    }  
    
    /** 
     * ��ӵ�Set�У�ͬʱ���ù���ʱ�䣩 
     * @param key keyֵ 
     * @param seconds ����ʱ�� ��λs 
     * @param value 
     * @return 
     */  
    public boolean addSet(String key,int seconds, String... value) {  
        boolean result = addSet(key, value);  
        if(result){  
            long i = expire(key, seconds);  
            return i==1;  
        }  
        return false;  
    }  
    /** 
     * ��ӵ�Set�� 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean addSet(String key, String... value) {  
        if(key == null || value == null){  
            return false;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.sadd(key, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("setList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
      
    /** 
     * @param key 
     * @param value 
     * @return �ж�ֵ�Ƿ������set�� 
     */  
    public boolean containsInSet(String key, String value) {  
        if(key == null || value == null){  
            return false;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.sismember(key, value);  
        } catch (Exception ex) {  
            logger.error("setList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
    /** 
     * ��ȡSet 
     * @param key 
     * @return 
     */  
    public  Set<String> getSet(String key){  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.smembers(key);  
        } catch (Exception ex) {  
            logger.error("getList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
  
    /** 
     * ��set��ɾ��value 
     * @param key 
     * @return 
     */  
    public  boolean removeSetValue(String key,String... value){  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.srem(key, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("getList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
      
      
      
    /** 
     * ��list��ɾ��value Ĭ��count 1 
     * @param key 
     * @param values ֵlist 
     * @return 
     */  
    public  int removeListValue(String key,List<String> values){  
        return removeListValue(key, 1, values);  
    }  
    /** 
     * ��list��ɾ��value 
     * @param key 
     * @param count  
     * @param values ֵlist 
     * @return 
     */  
    public  int removeListValue(String key,long count,List<String> values){  
        int result = 0;  
        if(values != null && values.size()>0){  
            for(String value : values){  
                if(removeListValue(key, count, value)){  
                    result++;  
                }  
            }  
        }  
        return result;  
    }  
    /** 
     *  ��list��ɾ��value 
     * @param key 
     * @param count Ҫɾ������ 
     * @param value 
     * @return 
     */  
    public  boolean removeListValue(String key,long count,String value){  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.lrem(key, count, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("getList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
      
    /** 
     * ��ȡList 
     * @param key  
     * @param start ��ʼλ�� 
     * @param end ����λ�� 
     * @return 
     */  
    public List<String> rangeList(String key, long start, long end) {  
        if (key == null || key.equals("")) {  
            return null;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.lrange(key, start, end);  
        } catch (Exception ex) {  
            logger.error("rangeList ����[key=" + key + " start=" + start + " end=" + end + "]" + ex.getMessage() , ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
      
    /** 
     * ���List���� 
     * @param key 
     * @return 
     */  
    public long countList(String key){  
        if(key == null ){  
            return 0;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.llen(key);  
        } catch (Exception ex) {  
            logger.error("countList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return 0;  
    }  
      
    /** 
     * ��ӵ�List�У�ͬʱ���ù���ʱ�䣩 
     * @param key keyֵ 
     * @param seconds ����ʱ�� ��λs 
     * @param value  
     * @return  
     */  
    public boolean addList(String key,int seconds, String... value){  
        boolean result = addList(key, value);  
        if(result){  
            long i = expire(key, seconds);  
            return i==1;  
        }  
        return false;  
    }  
    /** 
     * ��ӵ�List 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean addList(String key, String... value) {  
        if(key == null || value == null){  
            return false;  
        }  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.lpush(key, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("setList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
    /** 
     * ��ӵ�List(ֻ����) 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean addList(String key, List<String> list) {  
        if(key == null || list == null || list.size() == 0){  
            return false;  
        }  
        for(String value : list){  
            addList(key, value);  
        }  
        return true;  
    }  
      
    /** 
     * ��ȡList 
     * @param key 
     * @return 
     */  
    public  List<String> getList(String key){  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.lrange(key, 0, -1);  
        } catch (Exception ex) {  
            logger.error("getList error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
    /** 
     * ����HashSet���� 
     * 
     * @param domain ���� 
     * @param key    ��ֵ 
     * @param value  Json String or String value 
     * @return 
     */  
    public boolean setHSet(String domain, String key, String value) {  
        if (value == null) return false;  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.hset(domain, key, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("setHSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
    /** 
     * ���HashSet���� 
     * 
     * @param domain ���� 
     * @param key    ��ֵ 
     * @return Json String or String value 
     */  
    public String getHSet(String domain, String key) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.hget(domain, key);  
        } catch (Exception ex) {  
            logger.error("getHSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
  
    /** 
     * ɾ��HashSet���� 
     * 
     * @param domain ���� 
     * @param key    ��ֵ 
     * @return ɾ���ļ�¼�� 
     */  
    public long delHSet(String domain, String key) {  
        ShardedJedis shardedJedis = null;  
        long count = 0;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            count = shardedJedis.hdel(domain, key);  
        } catch (Exception ex) {  
            logger.error("delHSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return count;  
    }  
  
    /** 
     * ɾ��HashSet���� 
     * 
     * @param domain ���� 
     * @param key    ��ֵ 
     * @return ɾ���ļ�¼�� 
     */  
    public long delHSet(String domain, String... key) {  
        ShardedJedis shardedJedis = null;  
        long count = 0;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            count = shardedJedis.hdel(domain, key);  
        } catch (Exception ex) {  
            logger.error("delHSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return count;  
    }  
  
    /** 
     * �ж�key�Ƿ���� 
     * 
     * @param domain ���� 
     * @param key    ��ֵ 
     * @return 
     */  
    public boolean existsHSet(String domain, String key) {  
        ShardedJedis shardedJedis = null;  
        boolean isExist = false;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            isExist = shardedJedis.hexists(domain, key);  
        } catch (Exception ex) {  
            logger.error("existsHSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return isExist;  
    }  
  
    /** 
     * ȫ��ɨ��hset 
     * 
     * @param match fieldƥ��ģʽ 
     * @return 
     */  
    public List<Map.Entry<String, String>> scanHSet(String domain, String match) {  
        ShardedJedis shardedJedis = null;  
        try {  
            int cursor = 0;  
            shardedJedis = shardedJedisPool.getResource();  
            ScanParams scanParams = new ScanParams();  
            scanParams.match(match);  
            Jedis jedis = shardedJedis.getShard(domain);  
            ScanResult<Map.Entry<String, String>> scanResult;  
            List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>();  
            do {  
                scanResult = jedis.hscan(domain, String.valueOf(cursor), scanParams);  
                list.addAll(scanResult.getResult());  
                cursor = Integer.parseInt(scanResult.getStringCursor());  
            } while (cursor > 0);  
            return list;  
        } catch (Exception ex) {  
            logger.error("scanHSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
  
  
    /** 
     * ���� domain ָ���Ĺ�ϣ���������ֶε�valueֵ 
     * 
     * @param domain 
     * @return 
     */  
  
    public List<String> hvals(String domain) {  
        ShardedJedis shardedJedis = null;  
        List<String> retList = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            retList = shardedJedis.hvals(domain);  
        } catch (Exception ex) {  
            logger.error("hvals error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return retList;  
    }  
  
    /** 
     * ���� domain ָ���Ĺ�ϣ���������ֶε�keyֵ 
     * 
     * @param domain 
     * @return 
     */  
  
    public Set<String> hkeys(String domain) {  
        ShardedJedis shardedJedis = null;  
        Set<String> retList = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            retList = shardedJedis.hkeys(domain);  
        } catch (Exception ex) {  
            logger.error("hkeys error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return retList;  
    }  
  
    /** 
     * ���� domain ָ���Ĺ�ϣkeyֵ���� 
     * 
     * @param domain 
     * @return 
     */  
    public long lenHset(String domain) {  
        ShardedJedis shardedJedis = null;  
        long retList = 0;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            retList = shardedJedis.hlen(domain);  
        } catch (Exception ex) {  
            logger.error("hkeys error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return retList;  
    }  
  
    /** 
     * �������򼯺� 
     * 
     * @param key 
     * @param score 
     * @param value 
     * @return 
     */  
    public boolean setSortedSet(String key, long score, String value) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.zadd(key, score, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("setSortedSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
    /** 
     * ������򼯺� 
     * 
     * @param key 
     * @param startScore 
     * @param endScore 
     * @param orderByDesc 
     * @return 
     */  
    public Set<String> getSoredSet(String key, long startScore, long endScore, boolean orderByDesc) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            if (orderByDesc) {  
                return shardedJedis.zrevrangeByScore(key, endScore, startScore);  
            } else {  
                return shardedJedis.zrangeByScore(key, startScore, endScore);  
            }  
        } catch (Exception ex) {  
            logger.error("getSoredSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
  
    /** 
     * �������򳤶� 
     * 
     * @param key 
     * @param startScore 
     * @param endScore 
     * @return 
     */  
    public long countSoredSet(String key, long startScore, long endScore) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            Long count = shardedJedis.zcount(key, startScore, endScore);  
            return count == null ? 0L : count;  
        } catch (Exception ex) {  
            logger.error("countSoredSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return 0L;  
    }  
  
    /** 
     * ɾ�����򼯺� 
     * 
     * @param key 
     * @param value 
     * @return 
     */  
    public boolean delSortedSet(String key, String value) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            long count = shardedJedis.zrem(key, value);  
            return count > 0;  
        } catch (Exception ex) {  
            logger.error("delSortedSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
    /** 
     * ������򼯺� 
     * 
     * @param key 
     * @param startRange 
     * @param endRange 
     * @param orderByDesc 
     * @return 
     */  
    public Set<String> getSoredSetByRange(String key, int startRange, int endRange, boolean orderByDesc) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            if (orderByDesc) {  
                return shardedJedis.zrevrange(key, startRange, endRange);  
            } else {  
                return shardedJedis.zrange(key, startRange, endRange);  
            }  
        } catch (Exception ex) {  
            logger.error("getSoredSetByRange error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
  
    /** 
     * ��������� 
     * 
     * @param key 
     * @return 
     */  
    public Double getScore(String key, String member) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.zscore(key, member);  
        } catch (Exception ex) {  
            logger.error("getSoredSet error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return null;  
    }  
  
    public boolean set(String key, String value, int second) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.setex(key, second, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("set error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {  
            shardedJedis.close();//returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
    public boolean set(String key, String value) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.set(key, value);  
            return true;  
        } catch (Exception ex) {  
            logger.error("set error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {
        	shardedJedis.close();
            //shardedJedis.close();returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
    public String get(String key, String defaultValue) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.get(key) == null?defaultValue:shardedJedis.get(key);  
        } catch (Exception ex) {  
            logger.error("get error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {
        	shardedJedis.close();
            //returnResource(shardedJedis);  
        }  
        return defaultValue;  
    }  
  
    public boolean del(String key) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            shardedJedis.del(key);  
            return true;  
        } catch (Exception ex) {  
            logger.error("del error.", ex);  
           // returnBrokenResource(shardedJedis);  
        } finally {  
        	shardedJedis.close();
        	//returnResource(shardedJedis);  
        }  
        return false;  
    }  
  
    public long incr(String key) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.incr(key);  
        } catch (Exception ex) {  
            logger.error("incr error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally { 
        	shardedJedis.close();
            //returnResource(shardedJedis);  
        }  
        return 0;  
    }  
  
    public long decr(String key) {  
        ShardedJedis shardedJedis = null;  
        try {  
            shardedJedis = shardedJedisPool.getResource();  
            return shardedJedis.decr(key);  
        } catch (Exception ex) {  
            logger.error("incr error.", ex);  
            //returnBrokenResource(shardedJedis);  
        } finally {
        	shardedJedis.close();
            //returnResource(shardedJedis);  
        }  
        return 0;  
    }  
  
  
  
  /*  private void returnBrokenResource(ShardedJedis shardedJedis) {  
        try {  
            shardedJedisPool.returnBrokenResource(shardedJedis);  
        } catch (Exception e) {  
            logger.error("returnBrokenResource error.", e);  
        }  
    }  
  
    private void returnResource(ShardedJedis shardedJedis) {  
        try {  
            shardedJedisPool.returnResource(shardedJedis);  
        } catch (Exception e) {  
            logger.error("returnResource error.", e);  
        }  
    } */ 
}
