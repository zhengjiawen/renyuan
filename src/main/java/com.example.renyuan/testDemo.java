package com.example.renyuan;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class testDemo {

    public static void main(String[] args) throws Exception
    {
        String path = "src/main/resources/";
        String dataName = "data.txt";

        String encoding = "gbk";

        String filePath = path+dataName;
//        DataInputStream in=new DataInputStream(
//                new BufferedInputStream(
//                        new FileInputStream(filePath)));
//        String str = in.read();
//        System.out.print(str);


//        ObjectInputStream objectInputStream = new ObjectInputStream(freader);
//        Map map1=(HashMap<String, String>) objectInputStream.readObject();
//        freader.close();
//
////        String lineTxt = null;
//        Iterator<Map.Entry<String, String>> iter = map1.entrySet().iterator();
//        while(iter.hasNext())
//        {
//            Map.Entry entry = iter.next();
//            System.out.println(entry.toString());
//        }

    }
}
