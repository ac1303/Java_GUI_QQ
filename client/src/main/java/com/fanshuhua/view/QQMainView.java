package com.fanshuhua.view;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.Model.UserInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author 范书华
 * @create 2022/6/15 15:02
 */
public class QQMainView extends JFrame implements ActionListener, ItemListener {
    JScrollPane msgScrollPane, friendScrollPane, groupScrollPane;
    UserInfo userInfo;

    public void init(UserInfo userInfo) {
        this.userInfo = userInfo;
        setSize(300,680);
        setUndecorated(true);
        setLayout(null);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(this.getClass().getResource("/images/QQLoginView/qq.png")).getImage());
//        setIconImage(Toolkit.getDefaultToolkit().getImage("res/images/QQLoginView/qq.png"));
        setVisible(true);
    }

    /**
     * qq主视图
     */
    public void qqMainView(){
        JButton closeBtn = new JButton("");
        add(closeBtn);
        closeBtn.setBounds(270,5,30,30);
//        设置为透明
        closeBtn.setContentAreaFilled(false);
//        设置背景图片
        closeBtn.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/关 闭.png"), "关闭"));
//        监听点击关闭当前窗口
        closeBtn.addActionListener(e -> dispose());
//        设置按钮可见
        closeBtn.setVisible(true);
//        去掉边框线
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusPainted(false);

        JButton minBtn = new JButton("");
        add(minBtn);
        minBtn.setBounds(240,5,30,30);
        minBtn.setContentAreaFilled(false);
        minBtn.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/最小化.png"), "最小化"));
        minBtn.addActionListener(e -> setState(Frame.ICONIFIED));
        minBtn.setVisible(true);
        minBtn.setBorderPainted(false);
        minBtn.setFocusPainted(false);

        JLabel top = new JLabel("");
        add(top);
        top.setBounds(0,0,300,40);
        final int[] address = new int[2];
        top.setBackground(Color.white);
//        设置边框
//        top.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
//        监听鼠标按下
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


        JLabel picture = new JLabel();
        add(picture);
        picture.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/QQ20#dbdbdb.png")));
        picture.setBounds(10,10,30,30);

        JLabel head = new JLabel("");
        add(head);
        head.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQfaces/LargeImage/"+userInfo.getAvatar()+".jpg")));
        head.setBounds(10,50,100,100);

        JLabel name = new JLabel(userInfo.getNickname());
        add(name);
        name.setBounds(70,60,200,50);
        name.setFont(new Font("宋体", Font.BOLD, 16));

//        个性签名
        JLabel signature = new JLabel(userInfo.getSignature());
        add(signature);
        signature.setBounds(70,90,200,50);
        signature.setFont(new Font("宋体", Font.BOLD, 16));

        JLabel bg = new JLabel();
        add(bg);
        bg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/bg.gif")));
        bg.setBounds(0,0,300,150);

//        设置选项卡
        JPanel tab = new JPanel();
        add(tab);
        tab.setLayout(null);
        tab.setBounds(0,150,300,41);

        JLabel tab1_line = new JLabel("");
        tab.add(tab1_line);
        tab1_line.setBounds(0,40,100,1);
        JLabel tab1 = new JLabel("消息");
        tab.add(tab1);
        tab1.setBounds(0,0,100,40);
        tab1.setFont(new Font("宋体", Font.PLAIN, 18));
        tab1.setForeground(Color.gray);
        tab1.setBackground(new Color(0xF2F4F5));
        tab1.setOpaque(true);
        tab1.setHorizontalAlignment(SwingConstants.CENTER);
//        添加监听，鼠标移入时变色，移除时变回原色
        tab1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tab1.setForeground(Color.BLUE);
                tab1_line.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                tab1.setForeground(Color.gray);
//                设置边框颜色为透明
                tab1_line.setBorder(BorderFactory.createLineBorder(new Color(0xF2F4F5)));
            }
            //            点击事件
            @Override
            public void mouseClicked(MouseEvent e) {
                msgScrollPane.setVisible(true);
                friendScrollPane.setVisible(false);
                groupScrollPane.setVisible(false);
            }
        });

        JLabel tab2_line = new JLabel("");
        tab.add(tab2_line);
        tab2_line.setBounds(100,40,100,1);
        JLabel tab2 = new JLabel("联系人");
        tab.add(tab2);
        tab2.setBounds(100,0,100,40);
        tab2.setFont(new Font("宋体", Font.PLAIN, 18));
        tab2.setForeground(Color.gray);
        tab2.setBackground(new Color(0xF2F4F5));
        tab2.setOpaque(true);
        tab2.setHorizontalAlignment(SwingConstants.CENTER);
//        添加监听，鼠标移入时变色，移除时变回原色
        tab2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tab2.setForeground(Color.BLUE);
                tab2_line.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                tab2.setForeground(Color.gray);
//                设置边框颜色为透明
                tab2_line.setBorder(BorderFactory.createLineBorder(new Color(0xF2F4F5)));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                msgScrollPane.setVisible(false);
                friendScrollPane.setVisible(true);
                groupScrollPane.setVisible(false);
            }
        });

        JLabel tab3_line = new JLabel("");
        tab.add(tab3_line);
        tab3_line.setBounds(200,40,100,1);
        JLabel tab3 = new JLabel("群聊");
        tab.add(tab3);
        tab3.setBounds(200,0,100,40);
        tab3.setFont(new Font("宋体", Font.PLAIN, 18));
        tab3.setForeground(Color.gray);
        tab3.setBackground(new Color(0xF2F4F5));
        tab3.setOpaque(true);
        tab3.setHorizontalAlignment(SwingConstants.CENTER);
        //        添加监听，鼠标移入时变色，移除时变回原色
        tab3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                tab3.setForeground(Color.BLUE);
                tab3_line.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                tab3.setForeground(Color.gray);
//                设置边框颜色为透明
                tab3_line.setBorder(BorderFactory.createLineBorder(new Color(0xF2F4F5)));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                msgScrollPane.setVisible(false);
                friendScrollPane.setVisible(false);
                groupScrollPane.setVisible(true);
            }
        });
    }

    /**
     * 设置消息列表
     */
    public void setMsgScrollPane() {
        msgScrollPane = new JScrollPane();
        add(msgScrollPane);
        msgScrollPane.setBounds(0,190,300,460);
//        去掉边框
        msgScrollPane.setBorder(null);
//        设置滚动条
        msgScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel jPanel = new JPanel();
        JLabel[] jls = new JLabel[3];
        for(int i=0;i<jls.length;i++){
            //new JLabel(文本,图片地址,放的位置);
            jls[i] = new JLabel("消息"+(i+1),new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/头像 男孩.png")),JLabel.LEFT);
            jPanel.add(jls[i]);
        }
        jPanel.setLayout(new GridLayout(50, 1, 0,10));
        msgScrollPane.setViewportView(jPanel);
    }

    /**
     * 设置联系人列表
     */
    public void setFriendScrollPane() {
        friendScrollPane = new JScrollPane();
        add(friendScrollPane);
        friendScrollPane.setBounds(0,190,300,460);
//        去掉边框
        friendScrollPane.setBorder(null);
//        设置滚动条
        friendScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel jPanel = new JPanel();
        JSONObject friends = userInfo.getFriends();
        JLabel[] jls = new JLabel[friends.size()];
        for(int i=0;i<jls.length;i++){
            //new JLabel(文本,图片地址,放的位置);
            JSONObject friend = friends.getJSONObject(String.valueOf(i));
            String firendHead;
            jls[i]=new JLabel();
            if (friend.get("status").equals("在线")) {
                firendHead="images/QQfaces/LargeImage/"+friend.get("avatar")+".jpg";
                jls[i].setForeground(Color.BLUE);
            } else {
                firendHead="images/QQfaces/lixianImage/"+friend.get("avatar")+".jpg";
                jls[i].setForeground(Color.GRAY);
            }
            jls[i].setIcon(new ImageIcon(this.getClass().getClassLoader().getResource(firendHead)));
            jls[i].setText(friend.get("name")+"("+friend.get("status")+")");
            jls[i].setHorizontalAlignment(SwingConstants.LEFT);
//            设置点击事件
            int finalI = i;
            jls[i].addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseClicked(MouseEvent e) {
                                            JOptionPane.showMessageDialog(null, "点击了"+ finalI);
                                        }
                                    });
            jPanel.add(jls[i]);
        }
        jPanel.setLayout(new GridLayout(50, 1, 0,10));
        friendScrollPane.setViewportView(jPanel);
    }

    /**
     * 设置群组列表
     */
    public void setGroupScrollPane() {
        groupScrollPane = new JScrollPane();
        add(groupScrollPane);
        groupScrollPane.setBounds(0, 190, 300, 460);
//        去掉边框
        groupScrollPane.setBorder(null);
//        设置滚动条
        groupScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        JPanel jPanel = new JPanel();
        JSONObject groups = userInfo.getGroups();
        JLabel[] jls = new JLabel[groups.size()];
        for(int i=0;i<jls.length;i++){
            //new JLabel(文本,图片地址,放的位置);
            JSONObject group = groups.getJSONObject(String.valueOf(i));
            String groupHead="images/QQfaces/LargeImage/"+group.get("avatar")+".jpg";
            jls[i] = new JLabel((String) group.get("name"),new ImageIcon(this.getClass().getClassLoader().getResource(groupHead)),JLabel.LEFT);
            jPanel.add(jls[i]);
        }
        jPanel.setLayout(new GridLayout(50, 1, 0,10));
        groupScrollPane.setViewportView(jPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

}
