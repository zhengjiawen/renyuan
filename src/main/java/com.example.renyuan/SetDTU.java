package com.example.renyuan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.*;

public class SetDTU {
    public static int FANnumber=0;
    public static HashMap<String,String> map1 = new HashMap<String,String>();
    public static HashMap<String,String> MAN = new HashMap<String,String>();//运维员
    public static HashMap<String,String> MAN1 = new HashMap<String,String>();//主管
    static String[] DATA=new String[1000];
    static String[] man=new String[1000];
    static String[] man1=new String[1000];
    static String sMAN=null;
    static String sMAN1=null;
    static int p=0;
    static int manp=0;
    static int manp1=0;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        final JFrame frame =new JFrame("设置");
        Container container= frame.getContentPane();
        container.setLayout(null);
        JLabel label1 = new JLabel("设备-端口号：");
        label1.setBounds(50, 40, 150, 30);
        frame.add(label1);
        final TextField textField1 = new TextField(5);
        textField1.setBounds(200, 40, 200, 30);
        frame.add(textField1);
        JLabel label2 = new JLabel("设备-经纬度：");
        label2.setBounds(50, 80, 150, 30);
        frame.add(label2);
        final TextField textField21 = new TextField(10);
        textField21.setBounds(200, 80, 80, 30);
        frame.add(textField21);
        final TextField textField22 = new TextField(10);
        textField22.setBounds(320, 80, 80, 30);
        frame.add(textField22);
        JLabel label3 = new JLabel("设备-地址：");
        label3.setBounds(50, 120, 150, 30);
        frame.add(label3);
        final TextField textField3 = new TextField(15);
        textField3.setBounds(200, 120, 200, 30);
        frame.add(textField3);
        JLabel label4 = new JLabel("设备维修员及电话：");
        label4.setBounds(50, 160, 150, 30);
        frame.add(label4);
        final TextField textField4 = new TextField(9);
        textField4.setBounds(200, 160, 60, 30);
        frame.add(textField4);
        final TextField textField41 = new TextField(11);
        textField41.setBounds(270, 160, 130, 30);
        frame.add(textField41);
        JLabel label5 = new JLabel("设备电话及有效日期：");
        label5.setBounds(50, 200, 150, 30);
        frame.add(label5);
        final TextField textField5 = new TextField(11);
        textField5.setBounds(200, 200, 120, 30);
        frame.add(textField5);
        final TextField textField51 = new TextField(8);
        textField51.setBounds(330, 200, 70, 30);
        frame.add(textField51);
        JLabel label6 = new JLabel("数据1名称：");
        label6.setBounds(50, 240, 150, 30);
        frame.add(label6);
        final TextField textField6 = new TextField(18);
        textField6.setBounds(200, 240, 200, 30);
        frame.add(textField6);
        JLabel label61 = new JLabel("数据1报警值：");
        label61.setBounds(50, 280, 150, 30);
        frame.add(label61);
        final TextField textField61 = new TextField(2);
        textField61.setBounds(200, 280, 200, 30);
        frame.add(textField61);
        JLabel label62 = new JLabel("数据1说明：");
        label62.setBounds(50, 320, 150, 30);
        frame.add(label62);
        final JTextArea textField62 = new JTextArea();
        textField62.setLineWrap(true);//可以自动换行
        textField62.setBounds(200, 320, 200, 100);
        frame.add(textField62);
        JLabel label7 = new JLabel("数据2名称：");
        label7.setBounds(50, 430, 150, 30);
        frame.add(label7);
        final TextField textField7 = new TextField(18);
        textField7.setBounds(200, 430, 200, 30);
        frame.add(textField7);
        JLabel label71 = new JLabel("数据2报警值：");
        label71.setBounds(50, 470, 150, 30);
        frame.add(label71);
        final TextField textField71 = new TextField(2);
        textField71.setBounds(200, 470, 200, 30);
        frame.add(textField71);
        JLabel label72 = new JLabel("数据2说明：");
        label72.setBounds(50, 510, 150, 30);
        frame.add(label72);
        final JTextArea textField72 = new JTextArea();
        textField72.setLineWrap(true);//可以自动换行
        textField72.setBounds(200, 510, 200, 100);
        frame.add(textField72);
        JLabel label8 = new JLabel("主管、维修员端口、主管端口：");
        label8.setBounds(50, 620, 150, 30);
        frame.add(label8);
        final TextField textField8 = new TextField(4);
        textField8.setBounds(200, 620, 60, 30);
        frame.add(textField8);
        final TextField textField81 = new TextField(5);
        textField81.setBounds(270, 620, 60, 30);
        frame.add(textField81);
        final TextField textField82 = new TextField(5);
        textField82.setBounds(340, 620, 60, 30);
        frame.add(textField82);

        final JButton B1=new JButton("确认");
        B1.setBounds(100, 660, 200, 30);
        frame.add(B1);
        frame.setBounds(0, 0, 1200, 750);
        frame.setVisible(true);

        //文件导入1

        final ArrayList<String> l21=new ArrayList<String>();

        FileInputStream freader;
        try {
            freader = new FileInputStream("src/main/resources/setDTU.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            map1=(HashMap<String, String>) objectInputStream.readObject();
            freader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Iterator<Entry<String, String>> iter = map1.entrySet().iterator();

        while (iter.hasNext()) {
            Map.Entry entry = iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            String i=String.valueOf(key);
            String j=String.valueOf(val);
            DATA[p]=i+":"+j;
            p++;
        }

        JScrollPane jScrollPane3 = new JScrollPane();    //滚动条panel
        JList<String> myList1 = new JList<String>(DATA);

        jScrollPane3.setBounds(450, 40, 700, 650);
        jScrollPane3.setViewportView(myList1);
        frame.add(jScrollPane3);




        B1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s21=textField21.getText()+"%%";
                String s22=textField22.getText()+"%%";
                String s23=textField3.getText()+"%%";
                String s24=textField4.getText()+"%%";
                String s25=textField41.getText()+"%%";
                String s26=textField5.getText()+"%%";
                String s27=textField51.getText()+"%%";
                String s28=textField6.getText()+"%%";
                String s29=textField61.getText()+"%%";
                String s30=textField62.getText()+"%%";
                String s31=textField7.getText()+"%%";
                String s32=textField71.getText()+"%%";
                String s33=textField72.getText()+"%%";
                String s34=textField8.getText()+"%%";
                String s35=textField81.getText()+"%%";
                String s36=textField82.getText();
                l21.add(textField1.getText()+textField3.getText());

                map1.put(textField1.getText(),s21+s22+s23+s24+s25+s26+s27+s28+s29+s30+s31+s32+s33+s34+s35+s36);
            }
        });
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
//运维员、主管存档
                Iterator<Entry<String, String>> iter = map1.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    String i=String.valueOf(key);
                    String j=String.valueOf(val);
                    String[] xinxi=j.split("%%");
                    MAN.put(xinxi[3], (String)MAN.get(xinxi[3])+"%%"+i);
                    MAN1.put(xinxi[13], (String)MAN1.get(xinxi[13])+"%%"+i);
                }
                FileOutputStream file2 = null;
                try {
                    file2 = new FileOutputStream("src/main/resources/man.txt");
                } catch (FileNotFoundException e2) {
                    // TODO 自动生成的 catch 块
                    e2.printStackTrace();
                }
                try { // try语句块捕捉可能出现的异常
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(file2);
                    objectOutputStream.writeObject(MAN);
                    file2.close();
                } catch (Exception e1) { // catch处理该异常
                    e1.printStackTrace(); // 输出异常信息
                }
                FileOutputStream file3 = null;
                try {
                    file3 = new FileOutputStream("src/main/resources/zhuguan.txt");
                } catch (FileNotFoundException e2) {
                    // TODO 自动生成的 catch 块
                    e2.printStackTrace();
                }
                try { // try语句块捕捉可能出现的异常
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(file3);
                    objectOutputStream.writeObject(MAN1);
                    file3.close();
                } catch (Exception e1) { // catch处理该异常
                    e1.printStackTrace(); // 输出异常信息
                }
//设置存档
                FileOutputStream file = null;
                try {
                    file = new FileOutputStream("src/main/resources/setDTU.txt");
                } catch (FileNotFoundException e2) {
                    // TODO 自动生成的 catch 块
                    e2.printStackTrace();
                }
                try { // try语句块捕捉可能出现的异常
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
                    objectOutputStream.writeObject(map1);
                    file.close();
                } catch (Exception e1) { // catch处理该异常
                    e1.printStackTrace(); // 输出异常信息
                }


                Date today=new Date();
                String D=String.format("%tF",today);//年-月-日
                String sdata="src/main/resources/setDTU"+D+".txt";
                //System.out.println(D);
                FileOutputStream file1 = null;
                try {
                    file1 = new FileOutputStream(sdata);
                } catch (FileNotFoundException e2) {
                    // TODO 自动生成的 catch 块
                    e2.printStackTrace();
                }
                try { // try语句块捕捉可能出现的异常
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(file1);
                    objectOutputStream.writeObject(map1);
                    file1.close();
                } catch (Exception e1) { // catch处理该异常
                    e1.printStackTrace(); // 输出异常信息
                }
            }
        });

    }

}
