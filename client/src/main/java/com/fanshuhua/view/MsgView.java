package com.fanshuhua.view;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.Model.MsgViewVo;
import com.fanshuhua.webSocket.MsgWebSocket;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

public class MsgView extends JFrame{


    private JPanel jPanel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JButton jButton1;
    private JButton jButton2;
    private JTextArea jTextArea2;
    private JTextArea jTextArea1;
    //用户信息
    //好友id
    private String friendId;
    //好友name
    private String friendName;
    //好友状态
    private String friendStatus;
    //好友头像
    private String friendAvatar;
    private String userId;
    //用户name
    private String userName;
    {
        friendId="10001";
        friendName="董小绿";
        friendStatus="在线";
        friendAvatar="1";
        userId="10003";
        userName="龙破天";
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MsgView inst = new MsgView(new JSONObject());
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public MsgView(JSONObject json) {
        super();
//        this.friendId=json.getString("friendId");
//        this.friendName=json.getString("friendName");
//        this.friendStatus=json.getString("friendStatus");
//        this.friendAvatar=json.getString("friendAvatar");
//        this.userId=json.getString("userId");
//        this.userName=json.getString("userName");

        this.setUndecorated(true);
        initGUI();
        //窗体不能改变大小
        this.setResizable(false);
    }

    public void printFriendMsg(String msg){
        jTextArea1.append(friendName+"："+msg+"\r\n");
    }

    private void initGUI() {
        MsgWebSocket mws = new MsgWebSocket("ws://192.168.0.115:8080/webSocket/", "10003");
        mws.connect();
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jPanel1 = new JPanel();
                getContentPane().add(jPanel1, BorderLayout.CENTER);
                jPanel1.setLayout(null);

                {
                    jTextArea2 = new JTextArea();
                    JScrollPane scrollSend = new JScrollPane(jTextArea2);
                    jTextArea2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
                    scrollSend.setBounds(10, 499, 691, 96);
                    jTextArea2.setLineWrap(true);
                    jTextArea2.setWrapStyleWord(true);
                    scrollSend.getVerticalScrollBar().setUI(new MyScrollBarUI());
                    jPanel1.add(scrollSend);
                }
                {
                    jButton1 = new JButton("发送");
                    jButton1.setBounds(480, 607, 102, 31);
                    jButton1.setBorderPainted(false);
                    jButton1.setFocusPainted(false);
                    jButton1.setBackground(new Color(7,188,252));
                    jButton1.setForeground(Color.white);
                    jButton1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String msg=jTextArea2.getText();
                            if("在线".equals(friendStatus) && !"".equals(msg)){
                                jTextArea1.append(userName+"："+msg+"\r\n");
                                jTextArea2.setText("");
                                mws.sendMessage(msg,"friend","text",friendId);
                            }else if("".equals(msg)){
                                JOptionPane.showMessageDialog(null,"不可发送空文本！");
                            }else{
                                JOptionPane.showMessageDialog(null,"对方不在线，暂不可通讯！");
                            }
                        }
                    });
                    jPanel1.add(jButton1);
                }
                {

                    jButton2 = new JButton("关闭");
                    jButton2.setBounds(600, 607, 102, 31);
                    jButton2.setBorderPainted(false);
                    jButton2.setFocusPainted(false);
                    jButton2.setBackground(Color.white);
                    jButton2.setForeground(Color.black);
                    jButton2.addActionListener(e -> System.exit(0));
                    jPanel1.add(jButton2);
                }
                {
                    jTextArea1 = new JTextArea();JScrollPane scrollShow = new JScrollPane(jTextArea1);
                    scrollShow.setBounds(10, 84, 691, 402);
                    jTextArea1.setLineWrap(true);
                    jTextArea1.setWrapStyleWord(true);
                    jTextArea1.setEditable(false);
                    Font font = new Font("微软雅黑", Font.PLAIN, 20);
                    jTextArea1.setForeground(Color.BLACK);
                    jTextArea1.setFont(font);

                    scrollShow.getVerticalScrollBar().setUI(new MyScrollBarUI());
                    jPanel1.add(scrollShow);
                }
                {
                    jLabel3 = new JLabel();
                    jLabel3.setIcon(new ImageIcon("client/src/main/resources/images/QQfaces/LargeImage/"+friendAvatar+".jpg"));
                    jLabel3.setText(friendName);
                    jLabel3.setFont(new Font("微软雅黑", 0, 30));
                    jPanel1.add(jLabel3);
                    jLabel3.setBounds(38, 16, 500, 36);
                }
                {
                    if("在线".equals(friendStatus)){
                        jLabel4 = new JLabel("("+friendStatus+")");
                        jLabel4.setFont(new Font("微软雅黑", 0, 20));
                        jLabel4.setForeground(Color.green);
                        jPanel1.add(jLabel4);
                        jLabel4.setBounds(78, 42, 500, 36);
                    }else {
                        jLabel4 = new JLabel("(离线)");
                        jLabel4.setFont(new Font("微软雅黑", 0, 20));
                        jLabel4.setForeground(Color.GRAY);
                        jPanel1.add(jLabel4);
                        jLabel4.setBounds(78, 42, 500, 36);
                    }
                }
                {
                    JButton closeBtn = new JButton("");
                    jPanel1.add(closeBtn);
                    closeBtn.setBounds(670,10,30,30);
                    closeBtn.setContentAreaFilled(false);
                    closeBtn.setIcon(new ImageIcon("client/src/main/resources/images/QQLoginView/关 闭.png", "关闭"));
                    closeBtn.addActionListener(e -> System.exit(0));
                    closeBtn.setVisible(true);
                    closeBtn.setBorderPainted(false);
                    closeBtn.setFocusPainted(false);
                }
                {
                    JLabel top = new JLabel("");
                    jPanel1.add(top);
                    top.setBounds(0,0,711,40);
                    final int[] address = new int[2];
                    top.setBackground(Color.white);
                    top.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            address[0] =e.getPoint().x;
                            address[1] =e.getPoint().y;
                        }
                    });
                    //        监听鼠标移动
                    top.addMouseMotionListener(new MouseMotionAdapter() {
                        @Override
                        public void mouseDragged(MouseEvent e) {
                            int x = e.getPoint().x-address[0];
                            int y = e.getPoint().y-address[1];
                            setLocation(getX()+x,getY()+y);
                        }
                    });
                }
            }
            pack();
            setSize(711, 655);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}