package com.fanshuhua.Model;

import com.fanshuhua.view.MsgView;

import java.util.HashMap;
import java.util.Map;

public class MsgViewVo {
    public static Map<String, MsgView> map = new HashMap();

    public static MsgView getMap(String friendId){
        return map.get(friendId);
    }
    public static void setMap(String friendId,MsgView msgView){
        map.put(friendId,msgView);
    }
}
