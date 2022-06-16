package com.fanshuhua.webSocket;

import com.alibaba.fastjson.JSONObject;
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
        System.out.println("收到消息：" + s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("关闭连接");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("发生错误");
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
        MsgWebSocket client = new MsgWebSocket("ws://localhost:8080/webSocket/", "10001");
        client.connect();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.sendMessage("hello","friend","text","10002");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
