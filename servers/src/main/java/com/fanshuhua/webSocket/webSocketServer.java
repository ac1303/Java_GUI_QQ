package com.fanshuhua.webSocket;


import com.alibaba.fastjson.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 范书华
 * @create 2022/6/16 15:25
 */

@ServerEndpoint("/webSocket/{userId}")
public class webSocketServer {
  //线程安全的Map
    private static ConcurrentHashMap<String,Session> webSocketMap = new ConcurrentHashMap<String,Session>();//建立连接的方法

    @OnOpen
    public void onOpen(Session session,@PathParam("userId")String  userId) {
        System.out.println("连接成功");
        System.out.println("当前建立连接用户：" + userId);
        webSocketMap.put(userId,session);
    }
    @OnClose
    public void onClose(Session session){
        System.out.println("关闭连接");
        lostConnection(session);
    }

    @OnMessage
    public void onMessage(String message,Session session) {
//        System.out.println("收到"+getSessionId(session)+"消息：" + message);
        JSONObject object = JSONObject.parseObject(message);
        String type = object.getString("type");
        if (type.equals("friend")) {
            String toUserId = object.getString("toUserId");
            String message1 = object.getString("message");
            privateChat(session,message1,toUserId);
        }else if (type.equals("group")) {
            String groupId = object.getString("groupId");
            String message1 = object.getString("message");
            groupChat(session,message1,groupId);
        }
    }

    @OnError
    public void onError(Throwable error,Session session) {
        System.out.println("发生错误");
        lostConnection(session);
    }

    /**
     * 处理私聊消息
     *
     * @param session  发送人的会话
     * @param message  消息内容
     * @param toUserId 发送至
     */
    public void privateChat(Session session,String message,String toUserId){
//        查询发送人的会话
        Session session1 = webSocketMap.get(toUserId);
        String sender = getSessionId(session);
        JSONObject object = new JSONObject();
        object.put("sender",sender);
        object.put("message",message);
        if (session1 != null) {
            session1.getAsyncRemote().sendText(object.toJSONString());
//            object.put("status","成功");
        }else {
            object.put("status","失败");
//            描述
            object.put("description","对方不在线");
            session.getAsyncRemote().sendText(object.toJSONString());
        }
    }


    /**
     * 处理群聊消息
     * @param session  发送人的会话
     * @param message  消息内容
     * @param toGroupId 发送至
     * */
    public void groupChat(Session session,String message,String toGroupId) {
//        获取群聊成员
//        检测群聊成员是否在线，如果在线则发送消息，如果不在线跳过
        System.out.println("群聊消息：" + message);
    }


//    查询当前会话key
    public String getSessionId(Session session){
        for (String key : webSocketMap.keySet()) {
            if (webSocketMap.get(key) == session) {
                return key;
            }
        }
        return null;
    }

    /**
     * 处理失去连接的方法
     *
     * @param session 丢失会话
     */
    public static void lostConnection(Session session){
        //        查询对应id
        for (String key : webSocketMap.keySet()) {
            if (webSocketMap.get(key).equals(session)) {
                webSocketMap.remove(key);
                System.out.println("移除用户：" + key);
            }
        }
        System.out.println(webSocketMap);
    }

//    判断用户是否在线
    public static boolean isOnline(String userId){
        if (webSocketMap.get(userId) != null) {
            return true;
        }else {
            return false;
        }
    }
}
