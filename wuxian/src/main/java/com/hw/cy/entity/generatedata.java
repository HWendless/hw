package com.hw.cy.entity;

import com.hw.cy.pojo.rated;
import com.mao.spider.FastJsonUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

public class generatedata {
    //生成店铺数据
    public static void main(String[] args) throws Exception {
        String qd[] = {"美团", "饿了么", "线下", "其他"};
        List<String> list = new ArrayList<String>();
        MongoClient mongoClient = null;
        mongoClient = new MongoClient("172.30.7.143", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("WXYJ_CY1");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> collection = mongoDatabase
                .getCollection("cy_ele_Shop");
        // insert(collection);
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        //查询过程
        BasicDBObject query = new BasicDBObject();
        // query.put("id", "xxx");

        //查询结果
        //MongoCursor<Document> cursor = collection.find(query).skip(0).limit(10).iterator();
        MongoCursor<Document> cursor = collection.find(query).skip(0).iterator();
        while (mongoCursor.hasNext()) {


            list.add(mongoCursor.next().get("id").toString());

        }
        //经营数据
        MongoCollection<Document> collectioncy_inside_businessdata = mongoDatabase
                .getCollection("cy_inside_businessdata");
        //渠道数据
        MongoCollection<Document> collectioncy_inside_channels = mongoDatabase
                .getCollection("cy_inside_channels");
        MongoCollection<Document> collectioncy_inside_orderstatistics = mongoDatabase
                .getCollection("cy_inside_orderstatistics");
        for (int i = 0; i < list.size(); i++) {

            for (int j = 2555; j > 0; j--) {
                Document document1 = new Document();

                Document document3 = new Document();
                //销量
                int salesvolume = 0;

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();

                //过去七天
                c.setTime(new Date());
                c.add(Calendar.DATE, -j);
                Date d = c.getTime();
                String day = format.format(d);
                System.out.println("过去：" + day);
                //

                //渠道
//                private String channel;
                for (int k = 0; k< qd.length; k++) {
                    Document document2 = new Document();
                    int xl = (int) (30 + Math.random() * (100 - 1));
                    salesvolume += xl;
                    document2.put("channel", qd[k]);
//                //日期
//                private String inputdate;
                    document2.put("inputdate", day);
//                //销量
//                private  Integer salesvolume;
                    document2.put("salesvolume", xl);
//                //店铺id
//                private  String shopid;
                    document2.put("shopid", list.get(i));
                    //年
//                    private String inputyear;
                    document2.put("inputyear", day.substring(0, day.indexOf("-")));
                    //月
//                    private String inputmouth;
                    document2.put("inputmonth", day.substring(0, day.lastIndexOf("-")));
                    collectioncy_inside_channels.insertOne(document2);
                }

                //经营数据
                //日期
//                private String inputdate;
                document1.put("inputdate", day);
//                //销量
//                private  Integer salesvolume;
                document1.put("salesvolume", salesvolume);
//                //收入
//                private  Integer  income;
                document1.put("income", (int) (salesvolume * 10 + Math.random() * (salesvolume * 20 - salesvolume * 10 - 1)));
//                //成本
//                private  Integer cost;
                document1.put("cost", (int) (salesvolume * 5 + Math.random() * (salesvolume * 7 - salesvolume * 5 - 1)));
//                //对比
//                private  String contrast;
//                //店铺id
//                private  String shopid;
                document1.put("shopid", list.get(i));
                document1.put("inputyear", day.substring(0, day.indexOf("-")));
                //月
//                    private String inputmouth;
                document1.put("inputmonth", day.substring(0, day.lastIndexOf("-")));

                // document.put("food_names", r.getFood_names());


                //折线图
                //
//                //日期
//                private String inputdate;
                document3.put("inputdate", day);
//                //销量
//                private  Integer salesvolume;
                document3.put("salesvolume", salesvolume);
//                //新品销量
//                private  Integer  newsalesvolume;
                document3.put("newsalesvolume", (int)((int)salesvolume / 3) + Math.random() * (salesvolume - ((int) salesvolume / 3) - 1));
//                //店铺id
//                private  String shopid;
                document3.put("shopid", list.get(i));
                document3.put("inputyear", day.substring(0, day.indexOf("-")));
                //月
//                    private String inputmouth;
                document3.put("inputmonth", day.substring(0, day.lastIndexOf("-")));

                //插入
                collectioncy_inside_businessdata.insertOne(document1);

                collectioncy_inside_orderstatistics.insertOne(document3);

            }
        }

    }
}
