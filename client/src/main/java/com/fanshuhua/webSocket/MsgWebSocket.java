package com.fanshuhua.webSocket;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.Model.MsgViewVo;
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
        String friendId = jsonObject.getString("sender");
        System.out.println(friendId);
        MsgView msgView = MsgViewVo.getMap(friendId);
        if(msgView != null){
            msgView.printFriendMsg(jsonObject.getString("message"));
        }
//        MsgView msgView = new MsgView();
//        msgView.printFriendMsg(s);
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
        //    {
//        friendId="10003";
//        friendName="董小绿";
//        friendStatus="在线";
//        friendAvatar="1";
//        userId="10001";
//        userName="龙破天";
//    }
        String s = "{friendId:10003,friendName:'董小绿'," +
                "friendStatus:'在线',friendAvatar:1," +
                "userId:'10001',userName:'龙破天'}";
        JSONObject object = new JSONObject();
        object.put("friendId","10003");
        object.put("friendName","董小绿");
        object.put("friendStatus","在线");
        object.put("friendAvatar","1");
        object.put("userId","10001");
        object.put("userName","龙破天");

//        MsgWebSocket client = new MsgWebSocket("ws://192.168.0.115:8080/webSocket/", "10001");
//        client.connect();

        MsgView inst = new MsgView(object);
        inst.setLocationRelativeTo(null);
        inst.setVisible(true);
        MsgViewVo.setMap("10003",inst);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        client.sendMessage("hello","friend","text","10003");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
