package com.hd;

import com.hw.cy.pojo.geographicalPosition;
import com.mao.spider.FastJsonUtils;
import net.coobird.thumbnailator.Thumbnails;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class zip {
    public static void main(String[] args) throws Exception {
        List<String> lstring=new ArrayList<String>();
        File file = new File("E:\\yhpc-service\\yhpc\\tmp");
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                int i=1;
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        // traverseFolder2(file2.getAbsolutePath());
                    } else {
                        //System.out.println("文件:" + file2.getAbsolutePath());
                        List<geographicalPosition> glist=new ArrayList<geographicalPosition>();
                        List l=new ArrayList();
                        String pathname = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
                        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
                        //不关闭文件会导致资源的泄露，读写文件都同理
                        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271

                        FileReader reader = new FileReader(pathname);
                        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

                        System.out.println(pathname);

                         Thumbnails.of(pathname).scale(0.5f).outputQuality(0.5f).toFile("E:\\yhpc-service\\yhpc\\tmp1\\"+pathname.substring(pathname.lastIndexOf("\\"),pathname.length()));

//                        try {
//                            File f=new File(pathname);
//                            System.out.println(f.length());
//                           // FileInputStream fis   = new FileInputStream(f);
//                            if(f.length()>307200)
//                            {
//                                Thumbnails.of(pathname).scale(0.2f).outputQuality(0.2f).toFile("E:\\yhpc-service\\yhpc\\tmp1\\"+pathname.substring(pathname.lastIndexOf("\\"),pathname.length()));
//                            }
//                            else
//                            {
//                                Thumbnails.of(pathname).scale(0.3f).outputQuality(1f).toFile("E:\\yhpc-service\\yhpc\\tmp1\\"+pathname.substring(pathname.lastIndexOf("\\"),pathname.length()));
//                            }
//
//
//                        }
//                        catch (Exception e)
//                        {
//                            e.printStackTrace();
//                            System.out.println("---------");
//                            System.out.println(pathname);
//                            System.out.println("---------");
//
//
//
//
//                        }

                        ////////////

                    }
                    i++;
                }

            }
        } else {
            System.out.println("文件不存在!");
        }

    }
}


