package com.hw.cy.entity;

import com.alibaba.fastjson.JSONArray;
import com.hw.cy.pojo.geographicalPosition;
import com.hw.cy.pojo.rated;
import com.mao.spider.FastJsonUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class jsjsonfile {
    public static void main1(String[] args)throws Exception{


            File file = new File("E:\\qsj");
            if (file.exists()) {
                File[] files = file.listFiles();
                if (files.length == 0) {
                    System.out.println("文件夹是空的!");
                    return;
                } else {
                    for (File file2 : files) {
                        if (file2.isDirectory()) {
                            System.out.println("文件夹:" + file2.getAbsolutePath());
                           // traverseFolder2(file2.getAbsolutePath());
                        } else {
                            System.out.println("文件:" + file2.getAbsolutePath());
                            List<geographicalPosition> glist=new ArrayList<geographicalPosition>();
                            List l=new ArrayList();
                            String pathname = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
                            //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
                            //不关闭文件会导致资源的泄露，读写文件都同理
                            //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
                            FileReader reader = new FileReader(pathname);
                            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                            String line;

                            while ((line = br.readLine()) != null) {
                                // 一次读入一行数据

                                l.add(line);
                            }

                            Object o= FastJsonUtils.textToJson(l.get(0).toString());
                            Map m=FastJsonUtils.textTomap(o.toString());
                            Map mm=(Map) m.get("rst");
                            Object mmm=mm.get("business_info");
                            Map mmmm=FastJsonUtils.textTomap(mmm.toString());
                            System.out.println(mmmm);
                        }
                    }
                }
            } else {
                System.out.println("文件不存在!");
            }

    }
    //解析评论信息
    public static void main2(String[] args)throws Exception{


        File file = new File("E:\\餐饮数据\\都可");
        List<String> l=new ArrayList<String>();
        List<rated> lwtfile=new ArrayList<rated>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        // traverseFolder2(file2.getAbsolutePath());
                    } else {

                        if("评论".equals(file2.getName().split("_")[0]))
                        {
                            System.out.println("文件:" + file2.getName());
                            String pathname = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
                        FileReader reader = new FileReader(pathname);
                        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                        String line;

                        while ((line = br.readLine()) != null) {
                            // 一次读入一行数据

                            l.add(line);
                        }

                        Object o= FastJsonUtils.textToJson(l.get(0).toString());
                        Map m=FastJsonUtils.textTomap(o.toString());
                        List mm=(List)m.get("comments");
                 //       Object mmm=mm.get(0);
                    //    Map mmmm=FastJsonUtils.textTomap(mmm.toString());
                        for(Object lo:mm)
                        {
                            rated r=new rated();
                            String image="";
                            String food_names="";
                            String rated_at="";
                            String username="";
                            String rating_text="";
                            String rating="";

                            Map avater=FastJsonUtils.textTomap(lo.toString());
                            // 日期
                              rated_at=avater.get("rated_at").toString();
                            //拿到用户名称
                              username=avater.get("username").toString();
                            //评论内容
                              rating_text=avater.get("rating_text").toString();
                            //评分
                              rating=avater.get("rating").toString();
                            //拿吃的和图片
                            List<Map> order_images=(List<Map>)avater.get("order_images");
                            if(order_images!=null)
                            {
                                order_images.get(0).get("image_hash");
                                //List order_imagesmap=(List)order_images.get(0).toString();
                               // List listorder_images=(List) order_images.get(0).toString();
                                  image= order_images.get(0).get("image_hash").toString();
                                  food_names= order_images.get(0).get("food_names").toString();
                            }
                            System.out.println(image);
                            System.out.println(avater);
                            r.setRated_at(rated_at);
                            r.setUsername(username);
                            r.setRating_text(rating_text);
                            r.setRating(rating);
                            r.setFood_names(food_names);
                            r.setImage_hash(image);
                            r.setShopname(file2.getName().split("_")[1]);
                            lwtfile.add(r);

                        }
                       // System.out.println(mmmm);

                        }
//                        List<geographicalPosition> glist=new ArrayList<geographicalPosition>();
//                        List l=new ArrayList();
//                        String pathname = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
//                        FileReader reader = new FileReader(pathname);
//                        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
//                        String line;
//
//                        while ((line = br.readLine()) != null) {
//                            // 一次读入一行数据
//
//                            l.add(line);
//                        }
//
//                        Object o= FastJsonUtils.textToJson(l.get(0).toString());
//                        Map m=FastJsonUtils.textTomap(o.toString());
//                        Map mm=(Map) m.get("rst");
//                        Object mmm=mm.get("business_info");
//                        Map mmmm=FastJsonUtils.textTomap(mmm.toString());
//                        System.out.println(mmmm);
                    }
                }
                for(rated r:lwtfile)
                {
                    System.out.println(r);
                }

                MongoClient mongoClient = null;
                try {
                    mongoClient = new MongoClient("172.30.7.143", 27017);
                    MongoDatabase mongoDatabase = mongoClient.getDatabase("WXYJ_CY");
                    System.out.println("Connect to database successfully");
                    MongoCollection<Document> collection = mongoDatabase
                            .getCollection("ele_rated");
                    // insert(collection);
                    FindIterable<Document> findIterable = collection.find();
                    MongoCursor<Document> mongoCursor = findIterable.iterator();
                    while (mongoCursor.hasNext()) {
                        System.out.println(mongoCursor.next());
                    }
                    for(rated r:lwtfile)
                    {
                        Document document = new Document();
//                        private String food_names; //食物
//                        private String image_hash;//图片
//                        private String rating;//评分
//                        private String rated_at;//日期
//                        private String rating_text;//评论
//                        private String username;//用户名
//                        private String content;//回复信息
//                        private String created_at;//回复时间
//                        private String shopname;
                        document.put("food_names", r.getFood_names());
                        document.put("image_hash", r.getImage_hash());
                        document.put("rating", r.getRating());
                        document.put("rated_at",r.getRated_at());
                        document.put("rating_text",r.getRating_text());
                        document.put("username", r.getUsername());
                        document.put("content", r.getContent());
                        document.put("created_at",r.getCreated_at());
                        document.put("shopname", r.getShopname());
                        collection.insertOne(document);

                    }
                }catch (Exception e)
                {

                }
            }
        } else {
            System.out.println("文件不存在!");
        }

    }

    //解析评论信息
    public static void main(String[] args)throws Exception{


        File file = new File("E:\\餐饮数据\\都可");
        List<String> l=new ArrayList<String>();
        List<rated> lwtfile=new ArrayList<rated>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        // traverseFolder2(file2.getAbsolutePath());
                    } else {

                        if("评论".equals(file2.getName().split("_")[0]))
                        {
                            System.out.println("文件:" + file2.getName());
                            String pathname = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
                            FileReader reader = new FileReader(pathname);
                            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                            String line;

                            while ((line = br.readLine()) != null) {
                                // 一次读入一行数据

                                l.add(line);
                            }

                            Object o= FastJsonUtils.textToJson(l.get(0).toString());
                            Map m=FastJsonUtils.textTomap(o.toString());
                            List mm=(List)m.get("comments");
                            //       Object mmm=mm.get(0);
                            //    Map mmmm=FastJsonUtils.textTomap(mmm.toString());
                            for(Object lo:mm)
                            {
                                rated r=new rated();
                                String image="";
                                String food_names="";
                                String rated_at="";
                                String username="";
                                String rating_text="";
                                String rating="";

                                Map avater=FastJsonUtils.textTomap(lo.toString());
                                // 日期
                                rated_at=avater.get("rated_at").toString();
                                //拿到用户名称
                                username=avater.get("username").toString();
                                //评论内容
                                rating_text=avater.get("rating_text").toString();
                                //评分
                                rating=avater.get("rating").toString();
                                //拿吃的和图片
                                List<Map> order_images=(List<Map>)avater.get("order_images");
                                if(order_images!=null)
                                {
                                    order_images.get(0).get("image_hash");
                                    //List order_imagesmap=(List)order_images.get(0).toString();
                                    // List listorder_images=(List) order_images.get(0).toString();
                                    image= order_images.get(0).get("image_hash").toString();
                                    food_names= order_images.get(0).get("food_names").toString().replace("\"]","").replace("[\"","").replace("\"","");
                                }
                                System.out.println(image);
                                System.out.println(avater);
                                r.setRated_at(rated_at);
                                r.setUsername(username);
                                r.setRating_text(rating_text);
                                r.setRating(rating);
                                r.setFood_names(food_names);
                                r.setImage_hash(image);
                                r.setShopname(file2.getName().split("_")[1].trim());
                                lwtfile.add(r);

                            }


                        }
                    }
                }
                for(rated r:lwtfile)
                {
                    System.out.println(r);
                }

                MongoClient mongoClient = null;
                try {
                    mongoClient = new MongoClient("172.30.7.143", 27017);
                    MongoDatabase mongoDatabase = mongoClient.getDatabase("WXYJ_CY");
                    System.out.println("Connect to database successfully");
                    MongoCollection<Document> collection = mongoDatabase
                            .getCollection("ele_rated");
                    // insert(collection);
                    FindIterable<Document> findIterable = collection.find();
                    MongoCursor<Document> mongoCursor = findIterable.iterator();
                    while (mongoCursor.hasNext()) {
                        System.out.println(mongoCursor.next());
                    }
                    for(rated r:lwtfile)
                    {
                        Document document = new Document();
//                        private String food_names; //食物
//                        private String image_hash;//图片
//                        private String rating;//评分
//                        private String rated_at;//日期
//                        private String rating_text;//评论
//                        private String username;//用户名
//                        private String content;//回复信息
//                        private String created_at;//回复时间
//                        private String shopname;
                        document.put("food_names", r.getFood_names());
                        document.put("image_hash", r.getImage_hash());
                        document.put("rating", r.getRating());
                        document.put("rated_at",r.getRated_at());
                        document.put("rating_text",r.getRating_text());
                        document.put("username", r.getUsername());
                        document.put("content", r.getContent());
                        document.put("created_at",r.getCreated_at());
                        document.put("shopname", r.getShopname());
                        collection.insertOne(document);

                    }
                }catch (Exception e)
                {

                }
            }
        } else {
            System.out.println("文件不存在!");
        }

    }
}
