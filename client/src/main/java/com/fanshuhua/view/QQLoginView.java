package com.fanshuhua.view;

import com.alibaba.fastjson.JSONObject;
import com.fanshuhua.Model.WebSocket;
import com.fanshuhua.function.Login;
import com.fanshuhua.http.HttpRequest;
import com.fanshuhua.properties.PropertiesUtil;
import com.fanshuhua.webSocket.MsgWebSocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 范书华
 * @create 2022/6/14 21:47
 */
public class QQLoginView extends JFrame implements ActionListener, ItemListener {

    JButton loginBt;
    JButton registerBt;
    JPasswordField password;
    JTextField username;
    JLabel qq_input;

    public void init(){
        setSize(450,350);
//        setResizable(false);
        setUndecorated(true);
        setLayout(null);
//        设置窗口可以被拖动
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(this.getClass().getResource("/images/QQLoginView/qq.png")).getImage());
            setLocationRelativeTo(null);
        setVisible(true);
        setBackground(Color.red);
    }

    public void register() {
        JButton closeBtn = new JButton("");
        add(closeBtn);
        closeBtn.setBounds(420,10,30,30);
//        设置为透明
        closeBtn.setContentAreaFilled(false);
//        设置背景图片
        closeBtn.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/关 闭.png"), "关闭"));
//        监听点击
        closeBtn.addActionListener(e -> dispose());
//        设置按钮可见
        closeBtn.setVisible(true);
//        去掉边框线
        closeBtn.setBorderPainted(false);
        closeBtn.setFocusPainted(false);

        JButton minBtn = new JButton("");
        add(minBtn);
        minBtn.setBounds(380,10,30,30);
        minBtn.setContentAreaFilled(false);
        minBtn.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/最小化.png"), "最小化"));
        minBtn.addActionListener(e -> setState(Frame.ICONIFIED));
        minBtn.setVisible(true);
        minBtn.setBorderPainted(false);
        minBtn.setFocusPainted(false);

        JLabel picture = new JLabel();
        add(picture);
        picture.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/qq.png")));
        picture.setBounds(10,10,30,30);

        JLabel QQ = new JLabel("QQ");
        add(QQ);
        QQ.setBounds(50,15,100,30);
//        设置字体颜色为白色
        QQ.setForeground(Color.white);
        QQ.setFont(new Font("楷体",Font.BOLD,30));

        JLabel top = new JLabel("");
        add(top);
        top.setBounds(0,0,450,40);
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

        JLabel head = new JLabel("");
        add(head);
        head.setBounds(200,100,100,100);
        head.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/头像 男孩.png")));

//        设置分割线
        JLabel line = new JLabel(" ");
        add(line);
        line.setBounds(140,220,200,1);
        line.setBorder(BorderFactory.createLineBorder(Color.BLACK));

//        添加账号处的qq图标
        qq_input = new JLabel("");
        add(qq_input);
        qq_input.setBounds(140,190,30,30);
        qq_input.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/qq32#bfbfbf.png")));
//        添加账号输入框
        username = new JTextField();
        add(username);
        username.setBounds(170,190,170,30);
        username.setFont(new Font("宋体",Font.BOLD,20));
        username.setOpaque(false);
        username.setBorder(null);
//        设置焦点事件
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
//                替换图标
                qq_input.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/qq32#1296db.png")));
//                替换边框颜色
                line.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
            @Override
            public void focusLost(FocusEvent e) {
//                替换图标
                qq_input.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/qq32#bfbfbf.png")));
//                替换边框颜色
                line.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        });

        //        设置分割线
        JLabel pswLine = new JLabel(" ");
        add(pswLine);
        pswLine.setBounds(140,260,200,1);
        pswLine.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        添加密码处的图标
        JLabel psw_input = new JLabel("");
        add(psw_input);
        psw_input.setBounds(143,230,30,30);
        psw_input.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/锁定25#bfbfbf.png")));
//        添加账号输入框
        password = new JPasswordField();
        add(password);
        password.setBounds(170,230,170,30);
        password.setFont(new Font("宋体",Font.BOLD,20));
        password.setOpaque(false);
        password.setBorder(null);
//        设置焦点事件
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
//                替换图标
                psw_input.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/锁定25#1296db.png")));
//                替换边框颜色
                pswLine.setBorder(BorderFactory.createLineBorder(Color.BLUE));
            }
            @Override
            public void focusLost(FocusEvent e) {
//                替换图标
                psw_input.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/锁定25#bfbfbf.png")));
//                替换边框颜色
                pswLine.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
        });

        loginBt = new JButton("登录");
        add(loginBt);
        loginBt.setBounds(140,300,200,30);
//        取消按钮的边框
        loginBt.setBorderPainted(false);
//        取消按钮的焦点
        loginBt.setFocusPainted(false);
//        设置背景颜色#07BCFC
        loginBt.setBackground(new Color(7,188,252));
//        设置字体颜色为白色
        loginBt.setForeground(Color.white);
//        监听点击事件
        loginBt.addActionListener(e -> {
            String username = this.username.getText();
            char[] password = this.password.getPassword();
            if(!checkUsernameAndPassword(username,password)){
                return;
            }
            String res=HttpRequest.sendGet(PropertiesUtil.get("serverUrl")+"Login", "id="+username+"&password="+new String(password));
            JSONObject jsonObject = JSONObject.parseObject(res);
            if (Login.login(jsonObject)){
                dispose();
            }
        });

//        注册按钮
        registerBt = new JButton("注册账号");
        add(registerBt);
        registerBt.setBounds(0,320,90,30);
//        取消按钮的边框
        registerBt.setBorderPainted(false);
        registerBt.setContentAreaFilled(false);
//        设置字体颜色灰白
        registerBt.setForeground(Color.gray);
//        取消按钮的焦点
        registerBt.setFocusPainted(false);
//        点击跳转浏览器
        registerBt.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://im.qq.com/index"));
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        JLabel bg = new JLabel("");
        add(bg);
        bg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("images/QQLoginView/bg.gif")));
        bg.setBounds(0,0,450,150);

    }

    private boolean checkUsernameAndPassword(String username, char[] password) {
//        判断用户名是否为空
        if("".equals(username)||username==null){
            JOptionPane.showMessageDialog(null,"用户名不能为空","提示",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int maxLength = 12,minLength = 6;
        if (username.length() < minLength || username.length() > maxLength) {
            JOptionPane.showMessageDialog(null, "用户名长度必须在6-12位之间", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        }
//        判断密码是否为空
        if(password==null){
            JOptionPane.showMessageDialog(null,"密码不能为空","提示",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (password.length < minLength || password.length > maxLength) {
            JOptionPane.showMessageDialog(null, "密码长度必须在6-12位之间", "提示", JOptionPane.WARNING_MESSAGE);
            return false;
        }
//        判断用户名里面是否包含违规字符，用户名只能使用数字
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == ' ') {
                JOptionPane.showMessageDialog(null, "用户名不能包含空格", "提示", JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (!Character.isDigit(username.charAt(i))) {
                JOptionPane.showMessageDialog(null, "用户名只能使用数字", "提示", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
//        判断密码里面是否包含违规字符，密码只能使用数字和字母
        for (char c : password) {
            if (c == ' ') {
                JOptionPane.showMessageDialog(null, "密码不能包含空格", "提示", JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (!Character.isDigit(c) && !Character.isLetter(c)) {
                JOptionPane.showMessageDialog(null, "密码只能使用数字和字母", "提示", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        QQLoginView testView = new QQLoginView();
        testView.init();
        testView.register();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
