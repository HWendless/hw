package com.hw.cy.entity;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

public class findcy {

    public static void main(String[] args) {
        String qd[] = {"美团", "饿了么", "线下", "其他"};
        List<String> list = new ArrayList<String>();
        MongoClient mongoClient = null;
        mongoClient = new MongoClient("172.30.7.143", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("WXYJ_CY1");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> collection = mongoDatabase
                .getCollection("allshops");
        // insert(collection);
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        Set<String> set=new HashSet();
        //查询过程
        BasicDBObject query = new BasicDBObject();
        // query.put("id", "xxx");
        //查询结果
        //MongoCursor<Document> cursor = collection.find(query).skip(0).limit(10).iterator();
        MongoCursor<Document> cursor = collection.find(query).skip(0).iterator();
        while (mongoCursor.hasNext()) {
            Map map=new HashMap();
            map=mongoCursor.next();
            if(map.containsKey("Evaluate")&&(map.get("Evaluate")!=null))
            {
                String str=map.get("Evaluate").toString();
                System.out.println(str+"-----"+str.length());
                if(str.length()>=52)
                {
                    set.add(str);
                }
            }


        }


        MongoCollection<Document> collection1 = mongoDatabase
                .getCollection("cy_ele_Shop");
        // insert(collection);
        FindIterable<Document> findIterable1 = collection1.find();
        MongoCursor<Document> mongoCursor1 = findIterable1.iterator();
        Set<String> set1=new HashSet();
        //查询过程
        BasicDBObject query1 = new BasicDBObject();
        // query.put("id", "xxx");
        //查询结果
        //MongoCursor<Document> cursor = collection.find(query).skip(0).limit(10).iterator();
        MongoCursor<Document> cursor1 = collection.find(query1).skip(0).iterator();
        while (mongoCursor1.hasNext()) {
            Map map=new HashMap();
            map=mongoCursor1.next();
            if(map.containsKey("id")&&(map.get("id")!=null))
            {
                String str=map.get("id").toString();
                System.out.println(str);
                    set1.add(str);

            }


        }




        List<String> result = new ArrayList<String>(set);
        MongoCollection<Document> collectioncy_inside_orderstatistics = mongoDatabase
                .getCollection("cy_cy");
        for(String ssss:set1)
        {
            Document document1 = new Document();

            System.out.println(ssss);
            document1.put("word_cloud", result.get((int)(0+Math.random()*(result.size()-1))));
            document1.put("shopid", ssss);
            collectioncy_inside_orderstatistics.insertOne(document1);
        }

    }
}
