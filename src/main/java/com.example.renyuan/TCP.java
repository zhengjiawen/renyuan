package com.example.renyuan;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class TCP extends Thread{
    static int port=40200;
    BufferedReader reader; // 创建BufferedReader对象
    static OutputStream writer;
    static ServerSocket server; // 创建ServerSocket对象
    static Socket socket; // 创建Socket对象socket
    static String s1="30000-红立方";
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            server = new ServerSocket(port); // 实例化Socket对象
            System.out.println("服务器套接字已经创建成功"+port); // 输出信息
            while (true) { // 如果套接字是连接状态
                System.out.println("等待客户机的连接"); // 输出信息
                socket = server.accept(); // 实例化Socket对象
                writer=socket.getOutputStream();
                writer.write(s1.getBytes("utf-8"));
                writer.close();
                socket.close();
            }
        }catch (Exception e) {
            e.printStackTrace(); // 输出异常信息
        }
    }

}

