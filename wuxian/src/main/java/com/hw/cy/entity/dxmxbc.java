package com.hw.cy.entity;

import java.io.*;
import java.util.Map;

import static com.mao.spider.FastJsonUtils.stringToCollect;
import static com.mao.spider.FastJsonUtils.textToJson;

public class dxmxbc {
//    public static void main(String[] args) {
//      //  InputStream is = new FileInputStream("");
//       String s= readToString("C:\\蜜雪冰城数据\\蜜雪冰城(石一路)");
//      // Object ojson=textToJson(s);
//       Map sjson=stringToCollect(s);
//        Map m=(Map) sjson.get("rst");
//       System.out.println(m.get("name")+"---"+m.get("address")+"---"+m.get("phone")+"---"+m.get("latitude")+"---"+m.get("longitude")+"---"+m.get("business_info"));
//
//    }
public static void main(String[] args) throws IOException {
//

        readfile("C:\\蜜雪冰城数据");



}
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    //读取所有的文件
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());

            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());

                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }
}
