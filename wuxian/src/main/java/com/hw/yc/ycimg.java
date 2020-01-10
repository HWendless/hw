package com.hw.yc;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.io.FileUtils;
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

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ycimg {
    //http://www.5izhizao.com/api/_search?pretty&q=professionals.name:%E9%92%A3%E9%87%91&size=100



    public static void main(String[] args) throws  Exception{
        List<Map> L= new ArrayList();
        String pagelink="http://www.etmoc.com/Firms/Brandprice?page=";
        String infolink="http://www.etmoc.com/Firms/";

        MongoClient mongoClient = new MongoClient("192.168.60.79", 27017);

        MongoDatabase database = mongoClient.getDatabase("WXYJ_YC");

//        String httplink="https://www.michelin.com.cn/_api/dealer-locator/getDealerByCitySortedByDistance.php?city=%E8%8B%8F%E5%B7%9E&longitude=120.61990712&latitude=31.31798737&_=1575599987684";  //苏州
        for(int i=1;i<=349;i++)
        {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {

                client = HttpClients.createDefault();
                URIBuilder uriBuilder = new URIBuilder(pagelink+i);
                HttpGet httpGet = new HttpGet(uriBuilder.build());
                response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                String result =  EntityUtils.toString(entity, "UTF-8");
                // 创建saxReader对象

                Document document = Jsoup.parse(result);
                List<Element> links = document.getElementsByClass("li-p-t");
                List<Element> linkss = document.getElementsByClass("li-p-a");
                for(int j=1;j<links.size();j++) {
                    Map map = new HashMap();
                    //System.out.println("地址"+ links.get(j).toString());
                    Document d = Jsoup.parse(links.get(j).toString());
                    String cd = Jsoup.parse(linkss.get(j).toString()).text();
                    map.put("place_of_origind", cd);
                    Elements e = d.select("a[href]");
                    e.attr("href");
                    System.out.println("地址" + e.attr("href"));
                    String ycid = e.attr("href").toString().split("=")[1];


                    CloseableHttpClient client2 = null;
                    CloseableHttpResponse response2 = null;
                    client2 = HttpClients.createDefault();
                    URIBuilder uriBuilder2 = new URIBuilder(infolink + e.attr("href"));
                    HttpGet httpGet2 = new HttpGet(uriBuilder2.build());
                    response2 = client2.execute(httpGet2);
                    HttpEntity entity2 = response2.getEntity();
                    String result2 = EntityUtils.toString(entity2, "UTF-8");
                    // 创建saxReader对象
                    Document info = Jsoup.parse(result2);
                    //取图片和名字
                    List<Element> imglist = info.getElementsByClass("col-4");
                    Elements lmgname = imglist.get(0).select("img[src]");
                    String ename = info.getElementsByTag("small").get(0).text();
                    List<Element> infolist = info.getElementsByClass("col-8");

                    Document zzinfo = Jsoup.parse(infolist.get(0).toString());
                    List<Element> zzinfolist = zzinfo.getElementsByClass("proBar proBarB proBar2");
                    List<Element> zzinfolist2 = zzinfo.getElementsByClass("proBar");

                    map.put("src", lmgname.attr("src"));




                    CloseableHttpClient client3 = null;
                    CloseableHttpResponse response3 = null;
                    client3 = HttpClients.createDefault();
                    URIBuilder uriBuilder3 = new URIBuilder("http://www.etmoc.com" + lmgname.attr("src"));
                    HttpGet httpGet3 = new HttpGet(uriBuilder3.build());
                    response3 = client3.execute(httpGet3);
                    HttpEntity entity4 = response3.getEntity();
                    InputStream in = entity4.getContent();
                    FileUtils.copyInputStreamToFile(in, new File("C:\\ycimg"+lmgname.attr("src")));


                }







            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                response.close();
                client.close();
            }


        }







    }


}


