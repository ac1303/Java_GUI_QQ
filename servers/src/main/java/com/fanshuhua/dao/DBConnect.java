package com.fanshuhua.dao;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.properties.PropertiesUtil;
import com.fanshuhua.webSocket.webSocketServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 范书华
 * @create 2022/6/16 14:45
 */
public class DBConnect {
    private static PreparedStatement preparedStatement = null;
    private static Connection connection = null;
    private static ResultSet resultSet = null;

    static {
        try {
            connection = DriverManager.getConnection(PropertiesUtil.get("url"), PropertiesUtil.get("user"), PropertiesUtil.get("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    验证用户名和密码
    public static JSONObject checkIdAndPsw(String id, String psw) {
        String sql = "select * from qquser where q_id = ? and q_password = ?";
        JSONObject jsonObject = new JSONObject();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, psw);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                jsonObject.put("status", "success");
                jsonObject.put("description", "登录成功");
                JSONObject user = new JSONObject();
                user.put("id", resultSet.getString("q_id"));
                user.put("nickname", resultSet.getString("q_nickname"));
                user.put("avatar", resultSet.getInt("q_avatar"));
                user.put("signature", resultSet.getString("q_signature"));
                jsonObject.put("user", user);
            }else {
                jsonObject.put("status", "fail");
                jsonObject.put("description", "登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

//    获取好友信息
    public static JSONObject getFriendsInfo(String id) {
        String sql = "SELECT f_friendid,q_nickName,q_avatar,q_signature FROM friend INNER JOIN qquser ON friend.f_friendid=qquser.q_id WHERE friend.f_qid = ?";
        JSONObject jsonObject = new JSONObject();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                JSONObject friend = new JSONObject();
                friend.put("id", resultSet.getString("f_friendid"));
                friend.put("nickname", resultSet.getString("q_nickname"));
                friend.put("avatar", resultSet.getInt("q_avatar"));
                friend.put("signature", resultSet.getString("q_signature"));
                if (webSocketServer.isOnline(friend.getString("id"))) {
                    friend.put("status", "在线");
                } else {
                    friend.put("status", "离线");
                }
                jsonObject.put(String.valueOf(i), friend);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

//    获取群组信息
public static JSONObject getGroupsInfo(String id) {
        String sql = "SELECT qquser_group.g_groupId,g_name,g_avatar FROM qquser_group INNER JOIN `group` ON qquser_group.g_groupid=`group`.g_groupId WHERE q_id= ?";
        JSONObject jsonObject = new JSONObject();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                JSONObject group = new JSONObject();
                group.put("id", resultSet.getString("g_groupid"));
                group.put("nickname", resultSet.getString("g_name"));
                group.put("avatar", resultSet.getInt("g_avatar"));
                jsonObject.put(String.valueOf(i), group);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public static void main(String[] args) {
        System.out.println(checkIdAndPsw("100000", "123"));
        System.out.println(checkIdAndPsw("100000", "123456"));
        System.out.println(getFriendsInfo("100000"));
        System.out.println(getGroupsInfo("100000"));
    }
}
