package com.fanshuhua.properties;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 获取配置信息，然后保存到map中
 *
 * @author 范书华
 * @create 2022/6/13 16:34
 */
public class PropertiesUtil {
    private static final ConcurrentMap<String, String> CONFIG_MAP = new ConcurrentHashMap<>();
    private static boolean isLoad=false;
    private static final Properties PROP = new Properties();
    /**
     * 得到属性
     * 读取配置文件，然后以键值对的形式存储到configMap中
     *
     * @return boolean
     */
    private static boolean getProperties() {
        try {
            PROP.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("1/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (PROP.size()<=0){
            isLoad=false;
            return false;
        }
        for(Map.Entry<Object,Object> entry:PROP.entrySet()){
            String a=entry.getKey().toString().trim();
            String b=entry.getValue().toString().trim();
            if(a.length() ==0 || b.length() == 0){
                System.out.println("配置文件中有空值  key:"+a+" value:"+b);
            }else {
                if (CONFIG_MAP.containsKey(a)) {
                    System.out.println("配置文件中有重复的key:"+a);
                    continue;
                }
                CONFIG_MAP.put(a,b);
            }
        }
        System.out.println("配置加载成功");
        isLoad=true;
        return isLoad;
    }

    /**
     * 得到
     * 获取配置文件中的值
     *
     * @param key 关键
     * @return {@link String}
     */
    public static String get(String key){
        if (!isLoad){
            System.out.println("配置文件未加载");
            if (!getProperties()){
                System.out.println("程序返回空值！因为配置文件加载失败！");
                return null;
            }
        }
        String value=null;
        if (CONFIG_MAP.containsKey(key)) {
            value = CONFIG_MAP.get(key);
        }else {
            System.out.println("配置文件中不存在key:"+key);
        }
        return value;
    }
}
