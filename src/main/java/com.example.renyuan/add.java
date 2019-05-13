package com.example.renyuan;

import java.awt.Button;
import java.awt.Container;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class add {
    public static HashMap<String, String> map1 = new HashMap<String, String>();//设置信息
    public static HashMap<String, String> MAN = new HashMap<String, String>();//运维员客户端端口号	+设备端口号
    public static HashMap<String, String> WORKER = new HashMap<String, String>();//运维员+客户端端口号
    public static HashMap<String, String> MAN1 = new HashMap<String, String>();//主管客户端端口号+设备端口号
    public static HashMap<String, String> MANAGER = new HashMap<String, String>();//主管+客户端端口号
    public static HashMap<String, String> DATA1 = new HashMap<String, String>();//设备端口+数据1报警值
    public static HashMap<String, String> DATA2 = new HashMap<String, String>();//设备端口+数据2报警值
    public static HashMap<String, String> DATA10 = new HashMap<String, String>();    //设备端口+状态
    public static HashMap<String, String> DATAname1 = new HashMap<String, String>();//设备端口+数据1
    public static HashMap<String, String> DATAname2 = new HashMap<String, String>();//设备端口+数据2
    public static HashMap<String, String> adress = new HashMap<String, String>(); //设备端口+地址
    public static JList<String> myList1 = new JList<String>();
    public static String m2 = null;//工程师登录用户名
    static String[] DATA3 = new String[1000];//所有设备状态
    static String[] DATA4 = new String[1000];    //所有设备详细资料
    static String[] DATA5 = new String[1000];
    static String[] DATA40 = new String[100];    //运维员客户端：设备状态
    static String[] DATA41 = new String[100];    //主管客户端：设备状态
    public static int p = 0;
    public static int p1 = 0;
    public static int number = 0;

    //	private static Thread thread0; // 定义工程师线程
//	private static Thread[] thread; // 定义运维员线程
//	private static Thread[] thread1; // 定义主管线程
    public static String gaodeMAP;

    public static void checkFile(File file, long beginTime, int timeOut) throws InterruptedException, IOException {
        while (true) {

            if (file.lastModified() > beginTime) {
                System.out.println("modified...");
                File temp = new File(file.getParent() + "//~" + file.getName());
                while (true) {

                    if (file.renameTo(temp)) {
                        //recover   the   file
                        temp.renameTo(file);
                        break;
                    } else {
                        System.out.println("waiting   for   release");
                        Thread.sleep(1000);
                    }
                }
                break;
            } else {
                System.out.println("waiting...");
                break;
            }
        }
    }


    public static void main(String[] args) {

        //输入设置文件
        FileInputStream freader;
        try {
            freader = new FileInputStream("src/main/resources/setDTU.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            map1 = (HashMap<String, String>) objectInputStream.readObject();
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
            String i = String.valueOf(key);
            String j = String.valueOf(val);
            String[] xinxi = j.split("%%");
            MAN.put(xinxi[14], i + "%%" + MAN.get(xinxi[14]));
            MAN1.put(xinxi[15], i + "%%" + MAN1.get(xinxi[15]));
            WORKER.put(xinxi[14], xinxi[3]);//维修员+客户端端口
            MANAGER.put(xinxi[15], xinxi[13]);//主管+客户端端口
            DATA1.put(i, xinxi[8]);
            DATA2.put(i, xinxi[11]);
            DATAname1.put(i, xinxi[7]);
            DATAname2.put(i, xinxi[10]);
            adress.put(i, xinxi[2]);
            DATA5[p] = i;
            DATA4[p] = "设备端口号：" + i + ";" + "\n" + "设备-经纬度：" + xinxi[0] + "," + xinxi[1] + ";" + "\n" + "设备地址:" + xinxi[2]
                    + ";" + "\n" + "设备维修员及电话：" + xinxi[3] + "," + xinxi[4] + ";" + "\n" + "设备电话及有效日期：" + xinxi[5] + "," + xinxi[6]
                    + ";" + "\n" + "数据1名称：" + xinxi[7] + ";" + "\n" + "数据1报警值：" + xinxi[8] + ";" + "\n" + "数据1说明：" + xinxi[9]
                    + ";" + "\n" + "数据2名称：" + xinxi[10] + ";" + "\n" + "数据2报警值：" + xinxi[11] + ";" + "\n" + "数据2说明：" + xinxi[12]
                    + ";" + "\n" + "主管：" + xinxi[13];
            p++;
        }
        //窗体
        final JFrame frame = new JFrame("清能创新物联网");
        Container container = frame.getContentPane();
        container.setLayout(null);
        JLabel label1 = new JLabel("用户：");
        label1.setBounds(50, 10, 50, 20);
        frame.add(label1);
        final TextField textField1 = new TextField(11);
        textField1.setBounds(110, 10, 200, 20);
        frame.add(textField1);
        JLabel label2 = new JLabel("密码：");
        label2.setBounds(360, 10, 50, 20);
        frame.add(label2);
        final TextField textField2 = new TextField(11);
        textField2.setBounds(420, 10, 200, 20);
        frame.add(textField2);
        final JButton B1 = new JButton("确认");
        B1.setBounds(800, 10, 100, 20);
        frame.add(B1);
        //显示设备详细资料
        final JTextArea text = new JTextArea();
        text.setLineWrap(true);//可以自动换行
        text.setBounds(375, 75, 200, 600);
        frame.add(text);
        JLabel label3 = new JLabel("设备状态列表");
        label3.setBounds(150, 50, 100, 20);
        frame.add(label3);
        JLabel label4 = new JLabel("设备详细资料");
        label4.setBounds(440, 50, 100, 20);
        frame.add(label4);
        JLabel label5 = new JLabel("设备安装位置");
        label5.setBounds(900, 50, 100, 20);
        frame.add(label5);
        //JLabel label10 = new JLabel(new ImageIcon("E:/wz/logo1.png"));
        //label10.setBounds(0, 35, 1366, 5);
        // frame.add(label10);
        final JScrollPane jScrollPane3 = new JScrollPane();    //滚动条panel
        //显示地图

//		final JLabel MAP = new JLabel(new ImageIcon(gaodeMAP));
//	    MAP.setBounds(520, 75, 700, 600);
//	    frame.add(MAP);
        //显示设备状态列表


        frame.setBounds(0, 0, 1366, 750);
        frame.setVisible(true);
/*	    B1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String man2=textField1.getText()+textField2.getText();
               for(int n4=0;n4<man.length;n4++){
				if(man2.equals(man[n4]))
				{
					    Label label3 = new Label("OK!");
					    label3.setBounds(910, 10, 50, 20);
					    frame.add(label3);
					    m=textField1.getText();
					    break;
					}
				if(man2.equals(man1[n4]))
				{
					    Label label3 = new Label("OK!");
					    label3.setBounds(910, 10, 50, 20);
					    frame.add(label3);
					    m1=textField1.getText();
					    break;
					}
               }
               if(man2.equals(mAll))
               {
            	   Label label3 = new Label("OK!");
			    label3.setBounds(910, 10, 50, 20);
			    frame.add(label3);
			    m2="123";     */
        final JList<String> myList1 = new JList<String>(DATA3);
        myList1.setFixedCellHeight(15);
        final JLabel MAP1 = new JLabel();
        jScrollPane3.setBounds(50, 75, 300, 600);
        jScrollPane3.setViewportView(myList1);
        myList1.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                // TODO 自动生成的方法存根
                p1 = myList1.getSelectedIndex() + 30000;
//显示设备详细资料
                String p11 = p1 + "";
                String p12 = map1.get(p11);
                String p13;
                String[] xinxi = p12.split("%%");

                p13 = "设备端口号：" + p11 + ";" + "\n" + "设备-经纬度：" + xinxi[0] + "," + xinxi[1] + ";" + "\n" + "设备地址:" + xinxi[2]
                        + ";" + "\n" + "设备维修员及电话：" + xinxi[3] + "," + xinxi[4] + ";" + "\n" + "设备电话及有效日期：" + xinxi[5] + "," + xinxi[6]
                        + ";" + "\n" + "数据1名称：" + xinxi[7] + ";" + "\n" + "数据1报警值：" + xinxi[8] + ";" + "\n" + "数据1说明：" + xinxi[9]
                        + ";" + "\n" + "数据2名称：" + xinxi[10] + ";" + "\n" + "数据2报警值：" + xinxi[11] + ";" + "\n" + "数据2说明：" + xinxi[12]
                        + ";" + "\n" + "主管：" + xinxi[13];
                text.setText(p13);

                gaodeMAP = "D:/" + p1 + ".png";
                Icon icon = new ImageIcon(gaodeMAP);
                MAP1.setIcon(icon);
                //gaodeMAP="http://restapi.amap.com/v3/staticmap?location=113.770253,36.354339&zoom=14&size=700*600&markers=mid,,1:113.770253,36.354339&key=894d8cd60cfd460c59a26e92b450cc49";


                //gaodeMAP="http://restapi.amap.com/v3/staticmap?location="+DATA5[p1]
                //         +"&zoom=14&size=700*600&markers=mid,,1:"+DATA5[p1]+"&key=894d8cd60cfd460c59a26e92b450cc49";
            }
        });


        MAP1.setBounds(600, 75, 700, 600);
        frame.add(jScrollPane3);
        frame.add(MAP1);
 /*              }

               if((m==null)&&(m1==null)&&(m2==null))
				{   Label label4 = new Label("用户名或密码错误");
			    label4.setBounds(910, 10, 150, 20);
			    frame.add(label4);	}

		    }
	    });	    */


        //记事本路径地址
        String s1 = "src/main/resources/data";
        String s2;
        String s3 = ".txt";
        String s;
        int t = 30000; //记事本名字.
        while (p != 1001) {
            String data = null;
            for (int i = 0; i < p; i++) {
                s2 = String.valueOf(t + i);
                s = s1 + s2 + s3;
//                s = s1+s3;
                String filePath = s;
                File f = new File(s);


                try {
                    checkFile(f, System.currentTimeMillis(), (int) System.currentTimeMillis() + 100);
                } catch (InterruptedException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }

                int r = readTxtFile(filePath);
                int i1 = t + i;
                if (r == -1) {
                    DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":通讯中断-检查电源或充值。";
                }
                if (r == 0) {
                    String r1 = "0";
                    String r2 = "0";
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";
                }
                if (r == 1) {
                    String r1 = "1";
                    String r2 = "0";
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";
                }
                if (r == 2) {
                    String r1 = "0";
                    String r2 = "1";
                    number++;
                    System.out.println(number);
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";
                }
                if (r == 3) {
                    String r1 = "1";
                    String r2 = "1";
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";

                }
                data = data + DATA3[i] + "%%";
            }
//实时数据存档
            FileOutputStream file = null;
            try {
                file = new FileOutputStream("src/main/resources/data.txt");
            } catch (FileNotFoundException e2) {
                // TODO 自动生成的 catch 块
                e2.printStackTrace();
            }
            try { // try语句块捕捉可能出现的异常
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
                objectOutputStream.writeObject(data);
                file.close();
            } catch (Exception e1) { // catch处理该异常
                e1.printStackTrace(); // 输出异常信息
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }


    // 读取文件代码
    public static int readTxtFile(String filePath) {
        int abcd = 30000;//接口序列
        int r = -1;

        try {

            String encoding = "gbk";
            // 实例化文件
//			File file = readTxtFile(filePath);
            // 判断文件在该路径是否存在
// 判断文件是否存在
            // 准备读取文件
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(filePath), encoding);// 考虑到编码格式

            // 读取文件流
            BufferedReader bufferedReader = new BufferedReader(read);

            String lineTxt = null;

            // 循环遍历读取文件

            while ((lineTxt = bufferedReader.readLine()) != null) {
                String data1 = "7B 12 00 16 30 30 30 30 30 30 30 30 30 30 31 51 04 00 03 00 01 7B";
                String data0 = "7B 12 00 16 30 30 30 30 30 30 30 30 30 30 31 51 04 00 03 00 00 7B";
                String data2 = "7B 12 00 16 30 30 30 30 30 30 30 30 30 30 31 51 04 00 03 00 02 7B";
                String data3 = "7B 12 00 16 30 30 30 30 30 30 30 30 30 30 31 51 04 00 03 00 03 7B";

                lineTxt.replace(" ", "");

                //	String[] strs = lineTxt.split(",");
                //	System.out.println(strs[0]);
                //	for (int i=0;i<4;i++)
                {
                    if (lineTxt.indexOf(data1) != -1) {
                        r = 1;
                    }

                    if (lineTxt.indexOf(data0) != -1) {
                        r = 0;
                    }
                    if (lineTxt.indexOf(data2) != -1) {
                        r = 2;
                    }
                    if (lineTxt.indexOf(data3) != -1) {
                        r = 3;
                    }
                }


            }
            read.close();


        } catch (Exception e) {

            System.out.println("读取文件内容出错");

            e.printStackTrace();

        }
        FileOutputStream file2 = null;
        try {
            file2 = new FileOutputStream(filePath);
        } catch (FileNotFoundException e2) {
            // TODO 自动生成的 catch 块
            e2.printStackTrace();
        }
        try { // try语句块捕捉可能出现的异常
            file2.close();
        } catch (Exception e1) { // catch处理该异常
            e1.printStackTrace(); // 输出异常信息
        }
        return (r);
    }

    public static void initData3() {
        initData4();
        //记事本路径地址
        String s1 = "src/main/resources/data";
        String s2;
        String s3 = ".txt";
        String s;
        int t = 30000; //记事本名字.
//        while (p != 1001) {
        if(p != 0){
            String data = null;
            for (int i = 0; i < p; i++) {
                s2 = String.valueOf(t + i);
//                s = s1 + s2 + s3;
                s = s1+s3;
                String filePath = s;
                File f = new File(s);


                try {
                    checkFile(f, System.currentTimeMillis(), (int) System.currentTimeMillis() + 100);
                } catch (InterruptedException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }

                int r = readTxtFile(filePath);
                int i1 = t + i;
                if (r == -1) {
                    DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":通讯中断-检查电源或充值。";
                }
                if (r == 0) {
                    String r1 = "0";
                    String r2 = "0";
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";
                }
                if (r == 1) {
                    String r1 = "1";
                    String r2 = "0";
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";
                }
                if (r == 2) {
                    String r1 = "0";
                    String r2 = "1";
                    number++;
                    System.out.println(number);
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";
                }
                if (r == 3) {
                    String r1 = "1";
                    String r2 = "1";
                    if (r1.equals(DATA1.get("" + i1 + "")))
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = i1 + "-" + adress.get("" + i1 + "") + ":" + DATAname1.get("" + i1 + "") + "-" + "正常。";
                    if (r2.equals(DATA2.get("" + i1 + "")))
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "报警。";
                    else
                        DATA3[i] = DATA3[i] + DATAname2.get("" + i1 + "") + "-" + "正常。";

                }
            }
        }

    }

    public static void initData4()
    {
        //输入设置文件
        FileInputStream freader;
        try {
            freader = new FileInputStream("src/main/resources/setDTU.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(freader);
            map1 = (HashMap<String, String>) objectInputStream.readObject();
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
            String i = String.valueOf(key);
            String j = String.valueOf(val);
            String[] xinxi = j.split("%%");
            MAN.put(xinxi[14], i + "%%" + MAN.get(xinxi[14]));
            MAN1.put(xinxi[15], i + "%%" + MAN1.get(xinxi[15]));
            WORKER.put(xinxi[14], xinxi[3]);//维修员+客户端端口
            MANAGER.put(xinxi[15], xinxi[13]);//主管+客户端端口
            DATA1.put(i, xinxi[8]);
            DATA2.put(i, xinxi[11]);
            DATAname1.put(i, xinxi[7]);
            DATAname2.put(i, xinxi[10]);
            adress.put(i, xinxi[2]);
            DATA5[p] = i;
            DATA4[p] = "设备端口号：" + i + ";" + "\n" + "设备-经纬度：" + xinxi[0] + "," + xinxi[1] + ";" + "\n" + "设备地址:" + xinxi[2]
                    + ";" + "\n" + "设备维修员及电话：" + xinxi[3] + "," + xinxi[4] + ";" + "\n" + "设备电话及有效日期：" + xinxi[5] + "," + xinxi[6]
                    + ";" + "\n" + "数据1名称：" + xinxi[7] + ";" + "\n" + "数据1报警值：" + xinxi[8] + ";" + "\n" + "数据1说明：" + xinxi[9]
                    + ";" + "\n" + "数据2名称：" + xinxi[10] + ";" + "\n" + "数据2报警值：" + xinxi[11] + ";" + "\n" + "数据2说明：" + xinxi[12]
                    + ";" + "\n" + "主管：" + xinxi[13];
            p++;
        }
    }

}
