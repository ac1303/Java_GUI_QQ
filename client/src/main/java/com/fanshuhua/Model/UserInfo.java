package com.fanshuhua.Model;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 范书华
 * @create 2022/6/16 9:24
 */
public class UserInfo {
    private String id;
    private String nickname;
    private int avatar;
    private String signature;
    private JSONObject friends;
    private JSONObject groups;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public JSONObject getFriends() {
        return friends;
    }

    public void setFriends(JSONObject friends) {
        this.friends = friends;
    }

    public JSONObject getGroups() {
        return groups;
    }

    public void setGroups(JSONObject groups) {
        this.groups = groups;
    }
}
