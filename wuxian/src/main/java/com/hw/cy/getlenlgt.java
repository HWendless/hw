package com.hw.cy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getlenlgt {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("C:\\Users\\Administrator\\Desktop\\interpreter (4)");
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line;
        List<String> l=new ArrayList<String>();
        List<lat_lon> lat_lot_list=new ArrayList<lat_lon>();
        while ((line = br.readLine()) != null) {
            // 一次读入一行数据
            if(line.indexOf("lat")!=-1&&line.indexOf("lon")!=-1)
            {
                String ss[]=line.split("lat=")[1].split("lon=");
                String lon="";
                String lat="";
                lat_lon latlon=new lat_lon();
                for (int i=0;i<ss.length;i++)
                {
                    if(i==1)
                    {

                        latlon.setLon(ss[i].replace("\"","").replace("/>",""));

                    }
                    else
                    {

                        latlon.setLat(ss[i].replace("\"","").replace("\"",""));
                    }
                }
                    System.out.println(latlon.toString());
                lat_lot_list.add(latlon);
                l.add(line);
            }

        }
        FileWriter fw = new FileWriter("C:\\Users\\Administrator\\Desktop\\sz",true);
        BufferedWriter bw = new BufferedWriter(fw);
        for (lat_lon ll:lat_lot_list)
        {
            bw.write(ll.getLat()+","+ll.getLon());

        }
        bw.close();

//        for(String s:l)
//        {
//            System.out.println(s);
//        }
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
    static class lat_lon
    {
        public String lat;
        public String lon;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        @Override
        public String toString() {
            return "lat_lon{" +
                    "lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    '}';
        }
    }
}
