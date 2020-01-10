package com.hw.cy.entity;

import com.google.gson.Gson;
import com.hw.cy.other.IPBean;
import com.hw.cy.other.IPList;
import com.hw.cy.other.IPSpider;
import com.hw.cy.util.IPUtils;

import java.util.ArrayList;
import java.util.List;

public class geip {

    public static List<IPBean> getIPBean(){
        System.out.println("Start: ");
        List<IPBean> l=new ArrayList<IPBean>();
        IPSpider spider = new IPSpider();
        List<IPBean> list = spider.crawlHttp(3);

        System.out.println("爬取数量：" + list.size());

        Gson gson = new Gson();
        for (int i=0;i<list.size();i++) {
            final IPBean ipBean=list.get(i);
            System.out.println(gson.toJson(ipBean));

            new Thread(new Runnable() {
                public void run() {
                    boolean valid = IPUtils.isValid(ipBean);
                    if (valid){
                        IPList.add(ipBean);
                    }
                    IPList.increase();
                }
            }).start();
        }

        while (true){
            // 判断所有副线程是否完成
            if (IPList.getCount() == list.size()){
                System.out.println("有效数量：" + IPList.getSize());
                l=IPList.getlist();
                for(IPBean i:l)
                {
                    System.out.println(i.getIp()+":"+i.getPort());
                }

                break;
            }
        }
        return l;
    }
}
