package com.hw.cy.entity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class writefile {
  public static void writer(String name,String eleid,String s,String shi,String q,String nr) throws IOException {
        FileWriter fw = new FileWriter("E:\\一点点上海\\"+name+"_"+eleid+"_"+s+"_"+shi+"_"+q);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nr);
        bw.close();

         }
    public static void writersj(String name , String nr) throws IOException {
        FileWriter fw = new FileWriter("E:\\812\\数据\\"+name);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nr);
        bw.close();

    }
    public static void writerrz(String nr) throws IOException {
        FileWriter fw = new FileWriter("E:\\812\\日志",true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nr);
        bw.write("\n");
        bw.close();

    }
    public static void writertoke(String nr) throws IOException {
        FileWriter fw = new FileWriter("E:\\令牌");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nr);
        bw.close();

    }

}
