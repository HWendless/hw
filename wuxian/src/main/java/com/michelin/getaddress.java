package com.michelin;

import com.alibaba.fastjson.JSON;
import com.mao.spider.FastJsonUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sournergy.getCompany;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hw.cy.entity.FastJsonUtils.collectToString;
import static com.hw.cy.entity.FastJsonUtils.textToJson;

public class getaddress {
    //http://www.5izhizao.com/api/_search?pretty&q=professionals.name:%E9%92%A3%E9%87%91&size=100
    private  String link="http://www.5izhizao.com/api/_search?pretty&q=professionals.id:";
    private String professionalsName="";
    private int size=10;
//    //钣金 包装 注塑 机加工
//    private String [] professionalsNameLsit={"钣金","注塑","精密制造","机加工"};



    public static void main(String[] args) throws  Exception{

        MongoClient mongoClient = new MongoClient("192.168.60.79", 27017);

        MongoDatabase database = mongoClient.getDatabase("WXYJ_MICHELIN");
//        String httplink="https://www.michelin.com.cn/_api/dealer-locator/getDealerByCitySortedByDistance.php?city=%E8%8B%8F%E5%B7%9E&longitude=120.61990712&latitude=31.31798737&_=1575599987684";  //苏州
        String httplink="https://www.michelin.com.cn/_api/dealer-locator/getDealerByCitySortedByDistance.php?city=%E4%B8%8A%E6%B5%B7&longitude=120.61990712&latitude=31.31798737&_=1575602368042"; //上海
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(httplink);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result =  EntityUtils.toString(entity, "UTF-8");
         //   result=result.replace("[","").replace("]","");
             List L=JSON.parseArray(result);
            L.stream().forEach(a->{
                MongoCollection<Map> collection = database.getCollection("shop", Map.class);
                  collection.insertOne((Map)a);
            });


        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            response.close();
            client.close();
        }




        }



}
