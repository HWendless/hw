package com.sj_cy;

import com.hw.cy.entity.CREATE_UUID;
import com.hw.cy.pojo.ele_Food;
import com.hw.cy.pojo.ele_Shop;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class daochu {

    //解析店铺的信息和吃的和评论
    public static void main(String[] args)throws Exception {
        MongoClient mongoClient = null;
        mongoClient = new MongoClient("172.30.7.143", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("WXYJ_CY1");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> collection = mongoDatabase
                .getCollection("dm_cy");
        List<ele_Shop> listshop = new ArrayList<ele_Shop>();
        List<ele_Food> listfood = new ArrayList<ele_Food>();
        List<rated> listrated = new ArrayList<rated>();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String strdate = sdf.format(d);


//        File file = new File("E:\\812\\812\\数据");
        File file = new File("E:\\数据\\餐饮数据\\一点点上海");
        List<rated> lwtfile = new ArrayList<rated>();
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    po dmp = new po();
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        // traverseFolder2(file2.getAbsolutePath());
                    } else {

                        if (!"评论".equals(file2.getName().split("_")[0])) {
                            List<String> l = new ArrayList<String>();

                            System.out.println("文件:" + file2.getName());
                            String pathname = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
                            FileReader reader = new FileReader(pathname);
                            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                            String line;

                            while ((line = br.readLine()) != null) {
                                // 一次读入一行数据

                                l.add(line);
                            }
                            System.out.println("文件:" + file2.getName());
                            String pathnamepl = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
                            //存放商家的信息
                            ele_Shop ele_shop = new ele_Shop();
                            int commentscount = 0;
                            int good = 0;
                            int bad = 0;
                            int picture = 0;
                            int tastesgood = 0;
                            ele_shop.setCommentscount(commentscount);
                            ele_shop.setGood(good);
                            ele_shop.setBad(bad);
                            ele_shop.setPicture(picture);
                            ele_shop.setTastesgood(tastesgood);
                            Object dp = FastJsonUtils.textToJson(l.get(0).toString());
                            Map dpmap = FastJsonUtils.textTomap(dp.toString());
                            //拿地址
                            Map rst = (Map) dpmap.get("rst");
//                            String srst = rst.get("address").toString();
                            //拿店名
                            String sname = rst.get("name").toString();
                            dmp.setStore_name(sname);
                            //拿纬度
                            String slat = rst.get("latitude").toString();
                            dmp.setLatitude(slat);
                            //拿经度
                            String slon = rst.get("longitude").toString();
                            dmp.setLongitude(slon);
                            //拿电话
//                            String sphone = rst.get("phone").toString();
                            //获取唯一主键
                            String id = CREATE_UUID.getId();
                            //拿店铺的月销量
                            int recent_order_num = Integer.parseInt(rst.get("recent_order_num").toString());
                            dmp.setMonthly_sales(recent_order_num + "");
                            //拿店铺的评分
                            double shoprating = Double.parseDouble(rst.get("rating").toString());
                            String areaarray[] = file2.getName().split("_");
                            //省市区
                            dmp.setProvince(areaarray[2]);
                            dmp.setCity(areaarray[3]);
                            dmp.setDistrict(areaarray[4]);
                            //开始拿吃的
                            List<Map> menu = (List<Map>) dpmap.get("menu");
                            for (int k = 0; k < menu.size(); k++) {
                                //拿食品类型
                                String typename = menu.get(k).get("name").toString();
                                if ("热销".equals(typename))
                                    continue;
                                dmp.setClassification(typename);
                                //拿到吃的集合
                                List<Map> foods = (List<Map>) menu.get(k).get("foods");
                                for (int p = 0; p < foods.size(); p++) {
                                    //拿吃的原料
                                    String description = foods.get(p).get("description").toString();
                                    //拿吃的名字
                                    String foodname = foods.get(p).get("name").toString();
                                    dmp.setProduct_name(foodname);
                                    //拿吃的的月销量
                                    int foodmonth_sales = Integer.parseInt(foods.get(p).get("month_sales").toString());
                                    dmp.setFood_monthly_sales(foodmonth_sales + "");
                                    //拿吃的评分
                                    double foodrating = Double.parseDouble(foods.get(p).get("rating").toString());
                                    //拿吃的热度
                                    double satisfy_rate = Double.parseDouble(foods.get(p).get("satisfy_rate").toString());
                                    dmp.setDegree_of_heat(satisfy_rate + "");
                                    //单品最低价
                                    int lowest_price = (int) Double.parseDouble(foods.get(p).get("lowest_price").toString());
                                    dmp.setPrice(lowest_price + "");
                                    System.out.println(lowest_price);
//                                    ele_Food ele_food=new ele_Food();
//                                    ele_food.setDescription(description);
//                                    ele_food.setFoodname(foodname);
//                                    ele_food.setFoodrating(foodrating);
//                                    ele_food.setSatisfy_rate(satisfy_rate);
//                                    ele_food.setLowest_price(lowest_price);
//                                    ele_food.setFoodmonth_sales(foodmonth_sales);
//                                    ele_food.setInsertdata(strdate);
//                                    ele_food.setFoodtype(typename);
//                                    ele_food.setShopid(id);
//                                    listfood.add(ele_food);
                                    Document document = new Document();
                                    document.put("province", dmp.getProvince());
                                    document.put("city", dmp.getCity());
                                    document.put("district", dmp.getDistrict());
                                    document.put("store_name", dmp.getStore_name());
//                                    document.put("brand_name",areaarray[0].replace("（","(").indexOf("(")==-1?areaarray[0]:areaarray[0].replace("（","(").substring(0,areaarray[0].replace("（","(").indexOf("(")));
                                    document.put("brand_name","一点点");
                                    document.put("classification", dmp.getClassification());
                                    document.put("product_name", dmp.getProduct_name());
                                    document.put("price", dmp.getPrice());
                                    document.put("monthly_sales", dmp.getMonthly_sales());
                                    document.put("food_monthly_sales", dmp.getFood_monthly_sales());
                                    document.put("degree_of_heat", dmp.getDegree_of_heat());
                                    document.put("longitude", dmp.getLongitude());
                                    document.put("latitude", dmp.getLatitude());
                                    collection.insertOne(document);


                                }

                            }
                        }
                    }
                }

            }
        } else {
            System.out.println("文件不存在!");
        }
    }




}
