package com.fanshuhua.function;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.dao.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 范书华
 * @create 2022/6/16 8:51
 */
public class LoginAndRegister {

    public static JSONObject login(String username, String password) {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        JSONObject jsonObject = DBConnect.checkIdAndPsw(username, password);
        if (jsonObject.getString("status").equals("success")) {
//            获取好友信息
            JSONObject friends = DBConnect.getFriendsInfo(username);
            jsonObject.put("friends", friends);
//            获取群聊信息
            JSONObject groups = DBConnect.getGroupsInfo(username);
            jsonObject.put("groups", groups);
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

    public static void main(String[] args) {
        System.out.println(login("100000", "123456"));
    }
}
