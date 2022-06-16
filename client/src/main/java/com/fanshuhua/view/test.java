package com.fanshuhua.view;

import javax.swing.*;

/**
 * @author 范书华
 * @create 2022/6/16 10:51
 */
public class test extends JFrame {
    public test() {
        this.setTitle("test");
        this.setSize(500, 500);
        JTextField textField = new JTextField();
        JTextArea jTextArea = new JTextArea();
        this.add(jTextArea);
        jTextArea.setBounds(10, 10, 200, 30);
        jTextArea.setText("hello");
        jTextArea.append("asdhasdajk");
        jTextArea.setEditable(false);
//        jTextArea.setEnabled(false);
        jTextArea.setForeground(new java.awt.Color(255, 0, 0));
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new test();
    }
}
