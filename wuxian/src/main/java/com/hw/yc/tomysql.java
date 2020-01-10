package com.hw.yc;

import com.hw.cy.entity.FastJsonUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.bson.Document;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class tomysql {
    public static void main(String[] args) throws  Exception {


        MongoClient mongoClient = null;
        mongoClient = new MongoClient("192.168.60.79", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("WXYJ_YC");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> doc = mongoDatabase.getCollection("tobacco");

        FindIterable<Document> iter = doc.find();

        //初始化mybatis,创建SqlSessionFactory类的实例




        iter.forEach(new Block<Document>() {
         public void apply(Document _doc) {
             //数据库操作
             //定义读取文件名
             String resources = "mybatis-config.xml";
             //创建流
             Reader reader = null;
             try {
                 //读取mybatis-config.xml文件到reader对象中
                 reader = Resources.getResourceAsReader(resources);
             } catch (IOException e) {
                 e.printStackTrace();
             }
             SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
             //创建session实例
             SqlSession session = sqlMapper.openSession();
             Map map=new HashMap();
             Map m=new HashMap();
             map=FastJsonUtils.textTomap(_doc.toJson());
             System.out.println(map);
             if(!StringUtils.isBlank(map.get("packag_ingform")+""))
             {
                 String s=map.get("packag_ingform").toString();
                 m.put("packing",s.split("（")[0].trim());
                 m.put("box_packing",s.split("（")[1].trim().split("，")[0].replace("每盒","").replace("支",""));
                 m.put("strip_packing",s.split("（")[1].trim().split("，")[1].replace("每条","").replace("盒","").replace("）",""));
             }
             //map.get("packag_ingform");//包装，盒，只  条盒硬盒 （每盒 20 支，每条 10 盒）
             if(!StringUtils.isBlank(map.get("alt")+""))
             {
                 String s=map.get("alt").toString();
                 m.put("tobacco_brand",s.split("（")[0].trim());
                 if(s.split("（").length!=1&&s.split("（").length!=0)
                 m.put("tobacco_model",s.split("（")[1].trim().replace("）",""));
                 else
                     m.put("tobacco_model","");

             }
             map.get("alt");//分隔品牌和型号  白沙（和天下）
             if(!StringUtils.isBlank(map.get("bar_code")+""))
             {
                  m.put("bar_code",map.get("bar_code"));
             }
             map.get("bar_code");//条盒码

             if(!StringUtils.isBlank(map.get("src")+""))
             {
                 m.put("tobacco_img",map.get("src"));
             }
             m.put("id",map.get("ycid"));

             session.insert("insert_yc",m);
             session.commit();
             session.close();

    }
        });

    }
}
