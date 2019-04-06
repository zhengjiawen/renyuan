package com.example.renyuan;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Spring;
public class resp {
    public static int p=0;
    public static String data=null;
    public static HashMap<String,String> map1 = new HashMap<String,String>();//设置信息
    public static HashMap<String,String> map2 = new HashMap<String,String>();//设置信息-可传输
    /**
     14      * Socket服务端
     15      */
    public static void main(String[] args) {
        int i=40000;
        String str2="";
        int num;
        String str1="";
        //输入设置文件
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
            String k=String.valueOf(key);
            String j=String.valueOf(val);
            String[] xinxi=j.split("%%");
            map2.put(k,"设备端口号："+k+";"+"\n"+"设备-经纬度："+xinxi[0]+","+xinxi[1]+";"+"\n"+"设备地址:"+xinxi[2]
                    +";"+"\n"+"设备维修员及电话："+xinxi[3]+","+xinxi[4]+";"+"\n"+"设备电话及有效日期："+xinxi[5]+","+xinxi[6]
                    +";"+"\n"+"数据1名称："+xinxi[7]+";"+"\n"+"数据1报警值："+xinxi[8]+";"+"\n"+"数据1说明："+xinxi[9]
                    +";"+"\n"+"数据2名称："+xinxi[10]+";"+"\n"+"数据2报警值："+xinxi[11]+";"+"\n"+"数据2说明："+xinxi[12]
                    +";"+"\n"+"主管："+xinxi[13]);
            p++;
        }
        try {
            ServerSocket serverSocket=new ServerSocket(i);
            System.out.println("服务端已启动，等待客户端连接..");
            Socket socket=serverSocket.accept();//侦听并接受到此套接字的连接,返回一个Socket对象


            //根据输入输出流和客户端连接
            InputStream inputStream=socket.getInputStream();//得到一个输入流，接收客户端传递的信息
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//提高效率，将自己字节流转为字符流
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//加入缓冲区
            String temp=null;
            String info="";
            while((temp=bufferedReader.readLine())!=null){
                info=temp;
                System.out.println("已接收到客户端连接");
                System.out.println("服务端接收到客户端信息："+info+",当前客户端ip为："+socket.getInetAddress().getHostAddress());
                if(info.equals("user")) {
                    OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
                    PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
                    printWriter.print("yes");
                    printWriter.flush();

                }
                else if(info.equals("map3")) {
                    OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
                    PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
                    // 读取文件代码
                    FileInputStream freader1;
                    freader1 = new FileInputStream("src/main/resources/data.txt");
                    try {
                        // 准备读取文件
                        InputStreamReader read = new InputStreamReader(freader1);
                        // 读取文件流
                        BufferedReader bufferedReader1 = new BufferedReader(read);
                        data= bufferedReader1.readLine();
                        freader1.close();}
                    catch (Exception e) {
                        System.out.println("读取文件内容出错");
                        e.printStackTrace();
                    }
                    printWriter.print(data);

                    printWriter.flush();

                }
                else if(info.substring(0, 4).equals("map4")) {
                    OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
                    PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
                    printWriter.print(map2.get(info.substring(4, 9)));

                    printWriter.flush();

                }
            }

            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
            PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
            printWriter.print("你好，服务端已接收到您的信息");
            printWriter.flush();
            socket.shutdownOutput();//关闭输出流



            //关闭相对应的资源
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

