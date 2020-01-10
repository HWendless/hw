package com.hw.fhq;

import com.hw.cy.pojo.geographicalPosition;
import com.mao.spider.FastJsonUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class zlfile {
    public static void main(String[] args) throws Exception {
        List<String> lstring=new ArrayList<String>();
        int llll=0;
        String URL="https://xin.baidu.com/detail/patentContentAjax?referId=";
        File file = new File("E:\\123");
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
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
                        if(pathname.indexOf("专利列表")!=-1)
                        {
                            FileReader reader = new FileReader(pathname);
                            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                            System.out.println(pathname);
                            Object o= FastJsonUtils.textToJson(br.readLine());
                            Map m=FastJsonUtils.textTomap(o.toString());
                            List<Map> maplist=(List<Map>)m.get("list");
                            for(int i=0;i<maplist.size();i++)
                            {
//                                Map mmm=maplist.get(i);
////                                System.out.println(maplist.get(i));
////                                System.out.println(mmm.get("referId"));
//                                Map mm=getzl(URL+mmm.get("referId").toString());
//                                System.out.println(mm);
//                                lstring.add(mm.toString());
                            }
//                            FileReader reader = new FileReader(pathname);
//                            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
//                            String line;
//
//                            while ((line = br.readLine()) != null) {
//                                // 一次读入一行数据
//
//                                l.add(line);
//                            }
//
//                            Object o= FastJsonUtils.textToJson(l.get(0).toString());
//                            Map m=FastJsonUtils.textTomap(o.toString());
//                            Map mm=(Map) m.get("rst");
//                            Object mmm=mm.get("business_info");
//                            Map mmmm=FastJsonUtils.textTomap(mmm.toString());
//                            System.out.println(mmmm);

                        }

                    }
                }

            }
        } else {
            System.out.println("文件不存在!");
        }

    }
}
