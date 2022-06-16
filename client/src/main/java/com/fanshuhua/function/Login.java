package com.fanshuhua.function;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.Model.UserInfo;
import com.fanshuhua.Model.qqPublicVar;
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
            qqPublicVar.userInfo = new UserInfo();
            qqPublicVar.userInfo.setId(jsonObject.getJSONObject("user").getString("id"));
            qqPublicVar.userInfo.setNickname(jsonObject.getJSONObject("user").getString("nickname"));
            qqPublicVar.userInfo.setAvatar(jsonObject.getJSONObject("user").getInteger("avatar"));
            qqPublicVar.userInfo.setSignature(jsonObject.getJSONObject("user").getString("signature"));
            qqPublicVar.userInfo.setFriends(jsonObject.getJSONObject("friends"));
            qqPublicVar.userInfo.setGroups(jsonObject.getJSONObject("groups"));
            System.out.println(qqPublicVar.userInfo);
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

            qqPublicVar.qqMainView= new QQMainView();
            qqPublicVar.qqMainView.init(qqPublicVar.userInfo);
            qqPublicVar.qqMainView.qqMainView();
            qqPublicVar.qqMainView.setMsgScrollPane();
            qqPublicVar.qqMainView.setFriendScrollPane(qqPublicVar.userInfo.getFriends());
            qqPublicVar.qqMainView.setGroupScrollPane(qqPublicVar.userInfo.getGroups());
            return true;
        }else {
            System.out.println("登录失败");
            JOptionPane.showMessageDialog(null,"登录失败!"+jsonObject.get("description"));
            return false;
        }
    }
}
