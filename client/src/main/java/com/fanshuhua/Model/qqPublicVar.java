package com.fanshuhua.Model;

import com.fanshuhua.view.MsgView;
import com.fanshuhua.view.QQMainView;
import com.fanshuhua.webSocket.MsgWebSocket;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 范书华
 * @create 2022/6/17 1:27
 */
public class qqPublicVar {
    public static UserInfo userInfo;
    public static MsgWebSocket s;
    public static QQMainView qqMainView;
    public static ConcurrentHashMap<String, MsgView> msgViewList = new ConcurrentHashMap<>();
}
