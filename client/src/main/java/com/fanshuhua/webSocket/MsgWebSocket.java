package com.fanshuhua.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.Model.qqPublicVar;
import com.fanshuhua.view.MsgView;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author 范书华
 * @create 2022/6/16 15:42
 */
public class MsgWebSocket extends WebSocketClient {
    private String userId;
    public MsgWebSocket(String serverUri, String userId) {
        super(URI.create(serverUri+"/"+userId));
        this.userId = userId;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("连接成功");
    }

    @Override
    public void onMessage(String s) {
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
//        接收到用户上线下线消息
        if (jsonObject.getString("type").equals("在线")||jsonObject.getString("type").equals("离线")) {
            JSONObject friends=qqPublicVar.userInfo.getFriends();
            for (int i=0;i<friends.size();i++) {
                JSONObject friend = friends.getJSONObject(String.valueOf(i));
                if (friend.getString("id").equals(jsonObject.getString("userId"))) {
                    friend.put("status", jsonObject.getString("type"));
                    friends.put(String.valueOf(i),friend);
                    break;
                }
            }
            qqPublicVar.userInfo.setFriends(friends);
            qqPublicVar.qqMainView.setFriendScrollPane(friends);
        }else if (jsonObject.getString("type").equals("friend")) {
            String friendId = jsonObject.getString("sender");
            MsgView msgView = qqPublicVar.msgViewList.get(friendId);
            if (msgView != null) {
                msgView.printFriendMsg(jsonObject.getString("message"));
            }
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("关闭连接");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("发生错误");
        e.printStackTrace();
    }

    /**
     * 发送消息
     *
     * @param message  消息
     * @param type     群聊还是私聊
     * @param MsgType  消息类型
     * @param toUserId 发送给谁
     */
    public void sendMessage(String message,String type,String MsgType,String toUserId){
        JSONObject object = new JSONObject();
        object.put("userId",userId);
        object.put("type",type);
        object.put("MsgType",MsgType);
        object.put("toUserId",toUserId);
        object.put("message",message);
        System.out.println("发送消息：" + object.toJSONString());
        this.send(object.toJSONString());
    }

    public static void main(String[] args) {
        MsgWebSocket msgWebSocket = new MsgWebSocket("ws://localhost:8080/webSocket/","100001");
        msgWebSocket.connect();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
