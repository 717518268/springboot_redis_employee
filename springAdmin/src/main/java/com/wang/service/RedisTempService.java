package com.wang.service;

import java.util.Collection;

 
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.connection.DataType;


 /**
  * 在实现类里实现redis模板
  * @author Administrator
  *
  */
public interface RedisTempService {

    /**
     * 指定缓存失效时间
     *
     * @param key  key值
     * @param time 缓存时间
     */
    public void expire(String key, long time) ;
 
    /**
     * 判断key是否存在
     *
     * @param key 传入ke值
     * @return true 存在  false  不存在
     */
    public Boolean existsKey(String key)  ;
    /**
     * 判断key存储的值类型
     *
     * @param key key值
     * @return DataType[string、list、set、zset、hash]
     */
    public DataType typeKey(String key)  ;
 
    /**
     * 删除指定的一个数据
     *
     * @param key key值
     * @return true 删除成功，否则返回异常信息
     */
    public Boolean  deleteKey(String key)  ;
    /**
     * 删除多个数据
     *
     * @param keys key的集合
     * @return true删除成功，false删除失败
     */
    public Boolean deleteKey(Collection<String> keys) ;
    //-------------------- String ----------------------------
 
    /**
     * 普通缓存放入
     *
     * @param key   键值
     * @param value 值
     * @return true成功 要么异常
     */
    public Boolean setString(String key, Object value) ;
 
    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object getString(String key) ;
 
    /**
     * 设置缓存存在时间
     *
     * @param key   key值
     * @param value value值
     * @param time  时间 秒为单位
     * @return 成功返回true，失败返回异常信息
     */
    public boolean setString(String key, Object value, long time) ;
 
 
 
    //-----------------------------hash----------------------------------
 
    /**
     * 设置hash值,并设置过期时间
     *
     * @param key
     * @param hk
     * @param hv
     * @param time
     * @return
     */
    public Boolean setHash(String key, Object hk, Object hv, long time);
 
    public Boolean setHash(String key, Map map, long time) ;
 
    /**
     * 获取hash的值
     *
     * @param key
     * @param hk
     * @return
     */
    public Object getHash(String key, String hk)  ;
 
    /**
     * hash累加
     */
    public Long hincrease(String key, String hk, long l)  ;
    //----------------------------- list ------------------------------
 
    /**
     * 将list放入缓存
     *
     * @param key   key的值
     * @param value 放入缓存的数据
     * @return true 代表成功，否则返回异常信息
     */
    public Boolean setList(String key, Object value) ;
 
    /**
     * 将Object数据放入List缓存，并设置时间
     *
     * @param key   key值
     * @param value 数据的值
     * @param time  缓存的时间
     * @return true插入成功，否则返回异常信息
     */
    public Boolean setList(String key, Object value, long time) ;
 
    /**
     * 将list集合放入List缓存，并设置时间
     *
     * @param key   key值
     * @param value 数据的值
     * @param time  缓存的时间
     * @return true插入成功，否则返回异常信息
     */
    public Boolean setListAll(String key, Object value, long time);
    /**
     * 根据索引获取缓存List中的内容
     *
     * @param key   key的值
     * @param start 索引开始
     * @param end   索引结束 0 到 -1代表所有值
     * @return 返回数据
     */
    public List<Object> getList(String key, long start, long end) ;
 
    /**
     * 删除List缓存中多个list数据
     *
     * @param key   key值
     * @param count 移除多少个
     * @param value 可以传null  或者传入存入的Value的值
     * @return 返回删除了多少个
     */
    public long deleteListIndex(String key, long count, Object value);
    /**
     * 获取List缓存的数据
     *
     * @param key key值
     * @return 返回长度
     */
    public long getListSize(String key) ;
 
 
    //----------------------set-------------------
 
    /**
     * 判断是否包含在Set中
     *
     * @param key
     * @param o
     */
    public void isContainsKey(String key, HashSet o);
 
    //-----------------------lock----------------------
    /**
     * 获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 单位秒
     * @param waitTimeout 单位毫秒
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String requestId, int expireTime,long waitTimeout);
    /**
     * 释放锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String requestId) ;
    
    
    
    public long SetInSet(String key,Object obj);
    
    public Set GetInSet(String key);
    
    public Long DeleteInSet(String key,String value) ;

}
