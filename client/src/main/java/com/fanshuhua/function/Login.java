package com.fanshuhua.function;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.Model.UserInfo;
import com.fanshuhua.view.QQMainView;

import javax.swing.*;

/**
 * @author 范书华
 * @create 2022/6/16 9:26
 */
public class Login {
    public static boolean login(JSONObject jsonObject) {
        if (jsonObject.get("status").equals("success")){
            System.out.println("登录成功");
            UserInfo userInfo = new UserInfo();
            userInfo.setId(jsonObject.getJSONObject("user").getString("id"));
            userInfo.setNickname(jsonObject.getJSONObject("user").getString("nickname"));
            userInfo.setAvatar(jsonObject.getJSONObject("user").getInteger("avatar"));
            userInfo.setSignature(jsonObject.getJSONObject("user").getString("signature"));
            userInfo.setFriends(jsonObject.getJSONObject("friends"));
            userInfo.setGroups(jsonObject.getJSONObject("groups"));
            System.out.println(userInfo);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    QQMainView qqMainView = new QQMainView();
//                    qqMainView.init();
//                    qqMainView.qqMainView();
//                    qqMainView.setMsgScrollPane();
//                    qqMainView.setFriendScrollPane();
//                    qqMainView.setGroupScrollPane();
//                }
//            });

            QQMainView qqMainView = new QQMainView();
            qqMainView.init(userInfo);
            qqMainView.qqMainView();
            qqMainView.setMsgScrollPane();
            qqMainView.setFriendScrollPane();
            qqMainView.setGroupScrollPane();
            return true;
        }else {
            System.out.println("登录失败");
            JOptionPane.showMessageDialog(null,"登录失败!"+jsonObject.get("description"));
            return false;
        }
    }
}
