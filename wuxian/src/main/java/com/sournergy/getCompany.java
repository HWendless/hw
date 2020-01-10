package com.sournergy;

import com.alibaba.fastjson.JSON;
import com.hw.cy.elesj;
import com.hw.cy.entity.URLzh;
import com.mao.spider.FastJsonUtils;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.hw.cy.entity.FastJsonUtils.collectToString;
import static com.hw.cy.entity.FastJsonUtils.textToJson;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class getCompany {
    //http://www.5izhizao.com/api/_search?pretty&q=professionals.name:%E9%92%A3%E9%87%91&size=100
    private  String link="http://www.5izhizao.com/api/_search?pretty&q=professionals.id:";
    private String professionalsName="";
    private int size=10;
//    //钣金 包装 注塑 机加工
//    private String [] professionalsNameLsit={"钣金","注塑","精密制造","机加工"};

    //拿掉店的id之后，拿店的评论
    public static Map  httpRequestsize(String link, String professionalsid, int size) throws Exception {
       String httplink=link+ professionalsid+"&size="+size;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(httplink);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
           //  System.out.println("网页内容:"+result);
            Map map= FastJsonUtils.textTomap(result);
            Map mm= (Map) map.get("hits");
            int total=Integer.parseInt(mm.get("total").toString());
//            if( total>0)
//            {
//                int bs=total/100;
//                int ys=total%100;
//                if(bs>=1)
//                {
//                    for(int i=0;i<bs;i++)
//                    {
//                        httpRequest(link,professionalsName,total*bs);
//                    }
//
//                }
//            }

            return  httpRequest(link,professionalsid,total);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }

    }

    public static Map  httpRequest(String link, String professionalsid, int size) throws Exception {
        String httplink=link+ professionalsid+"&size="+size;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(httplink);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
           // System.out.println("网页内容:"+result);
            Map map= FastJsonUtils.textTomap(result);
            return  (Map) map.get("hits");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }

    }

    public static void main(String[] args) throws  Exception{

        MongoClient mongoClient = new MongoClient("192.168.60.79", 27017);

        MongoDatabase database = mongoClient.getDatabase("WXYJ_SUOJI");



//        DBObject bson = (DBObject)JSON.parse(json);


          String [] professionalsNameLsit={"钣金,425","注塑,395","精密铸造,428","机加工,424"};
        getCompany getcompany=new getCompany();
        getcompany.setProfessionalsName("钣金");

        for(int i=0;i<4;i++)
        {
            String array[]=professionalsNameLsit[i].split(",");
            Map map=httpRequestsize( getcompany.link,array[1],getcompany.size);
            System.out.println(map);
            List<Map>  list= (List<Map>) map.get("hits");

            for(Map o:list)
            {
                o.put("suojiid",o.get("_id").toString());
                o.remove("_id");
                o.put("type",array[0]);
//                Map map2=(Map) o.get("_source");
//                map2.put("type",array[0]);
//                MongoCollection<Map> collection = database.getCollection("company_information", Map.class);
                MongoCollection<Map> collection = database.getCollection("company", Map.class);
                collection.insertOne(o);

            }
            System.out.println(textToJson(collectToString(map)));
        }

   }

}
