package com.fanshuhua.Model;

import com.fanshuhua.properties.PropertiesUtil;
import com.fanshuhua.webSocket.MsgWebSocket;

/**
 * @author 范书华
 * @create 2022/6/17 0:00
 */
public class WebSocket {
    public static MsgWebSocket s;
//    创建连接
//    public static boolean connect(String userId){
//        s = new MsgWebSocket(PropertiesUtil.get("websocketUrl"), userId);
//        s.connect();
//        if (s.isOpen()) {
//            return true;
//        }else {
//            return false;
//        }
//    }
}
