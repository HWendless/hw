package com.mao.spider;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QFT {

    private static final String COMMON_URL = "https://www.dianping.com/search/keyword/6/0_七分甜/p1";


    public static void main(String[] args) throws IOException {

//        ReadExcel readExcel = new ReadExcel();
//        InputStream is = new FileInputStream("C:\\Users\\yraka\\Desktop\\coco\\七分甜\\苏州.xlsx");
//        List<List<String>> excelInfo = readExcel.getExcelInfo(is, "苏州.xlsx");
//        for(List<String> strings : excelInfo){
//            for(String string : strings){
//
//            }
//        }
//
//
//        //System.out.println("网页内容:"+result);
//            Document document = sendUrl(COMMON_URL);
//            //System.out.print(document);
//            //Elements elements = document.getElementsByClass("shop-list J_shop-list shop-all-list");
//            Elements pics = document.getElementsByClass("pic");
//            for(Element pic : pics){
//                System.out.println(pic);
//                Elements links = pic.select("a[href]");
//                for(Element link : links){
//                    String href = link.attr("href");
//                    Document document1 = sendUrl(href+"/review_all/p2");
//                    System.out.println(document1);
//
//                }
//            }


    }

    public static Document sendUrl(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("Cache-Control", "max-age=0");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", "_lxsdk_cuid=16bcf3f5e1ac8-00095b1590f1cb-3b654406-1fa400-16bcf3f5e1a1f; _lxsdk=16bcf3f5e1ac8-00095b1590f1cb-3b654406-1fa400-16bcf3f5e1a1f; _hc.v=\"\\\"f91a31ab-5f70-4c2b-86e2-8e98fbfc83b6.1562550165\\\"\"; s_ViewType=10; _dp.ac.v=d8cc17c9-7227-4829-84cd-efda82987d3d; ua=dpuser_8868560055; ctu=70ea41fd59e5150e5a8120436296887ec6b196cb6d92bb7165f1f3d569dae648; cy=6; cye=suzhou; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; lgtoken=0fbdea726-0352-4ea1-a5c5-96b95e90b0c8; dper=8c65a8b70d4b52ab93fa54d8b4bde1871fc7cdaabc7b16e7d3f8c94884caabc3e85973712ce785ce987406ec4b53b07a99fe88e913ce4781cda7947ec76ca4e2d81c503ba9da40800be45fb10a7855b36cf7ea20fa030bdb56c9289216be47e7; ll=7fd06e815b796be3df069dec7836c3df; _lxsdk_s=16bd4344e9d-58d-ec3-62b%7C%7C292");
            httpGet.setHeader("Host", "www.dianping.com");
            httpGet.setHeader("Upgrade-Insecure-Requests", "1");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);
            Document document = Jsoup.parse(result);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
}
