package com.hw.cy.entity;

import com.alibaba.fastjson.JSONObject;
import com.sun.javafx.collections.MappingChange;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class get_ip_bs {
    private final static String GET_IP_URL = "http://piping.mogumiao.com/proxy/api/get_ip_bs?appKey=e2d893b75ac3495e8107b6b12e15b140&count=5&expiryDate=0&format=1&newLine=2";
    public static List<Map> get_ip_dl( ) throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(10);
            Document doc = null;
            try {
                doc = Jsoup.connect(GET_IP_URL).get();
            } catch (IOException e) {

            }
            System.out.println(doc.text());
            String s=doc.text();
            Object os=FastJsonUtils.textToJson(s);
            Map m= (Map) os;
            if("0".equals(m.get("code").toString()))
            {
                List<Map> list=(List<Map>) m.get("msg");
                System.out.println(list);
                //  Thread.sleep(1000);
                return list;
            }
            return null;


        }
}
