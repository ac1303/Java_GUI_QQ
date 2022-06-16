package com.fanshuhua.function;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 范书华
 * @create 2022/6/16 8:51
 */
public class LoginAndRegister {

    public static JSONObject login(String username, String password) {
//        JSONObject jsonObject = new JSONObject();

        JSONObject jsonObject = new JSONObject();
//        状态码
        jsonObject.put("status", "success");
//       描述
        jsonObject.put("description", "登录成功");
//        好友数据
        JSONObject friends = new JSONObject();
        for (int i=0;i<=10;i++){
            JSONObject friend = new JSONObject();
            friend.put("id", i);
            friend.put("name", "好友"+i);
            friend.put("avatar",i+1);
            if (i%2==0) {
                friend.put("status", "离线");
            }else {
                friend.put("status", "在线");
            }
            friends.put(String.valueOf(i), friend);
        }
        jsonObject.put("friends", friends);
//        群聊数据
        JSONObject groups = new JSONObject();
        for (int i=0;i<=10;i++){
            JSONObject group = new JSONObject();
            group.put("id", i);
            group.put("name", "群聊"+i);
            group.put("avatar",i+1);
            groups.put(String.valueOf(i), group);
        }
        jsonObject.put("groups", groups);
//        用户信息
        JSONObject user = new JSONObject();
        user.put("id", username);
        user.put("nickname", "张三");
        user.put("avatar", 2);
        user.put("signature", "这是张三的个性签名");
        jsonObject.put("user", user);

        return jsonObject;
    }
}
