package com.hw.cy.entity;

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

public class cydata_js {
    //解析店铺的信息和吃的和评论
    public static void main(String[] args)throws Exception{
        List<ele_Shop> listshop=new ArrayList<ele_Shop>();
        List<ele_Food> listfood=new ArrayList<ele_Food>();
        List<rated>  listrated=new ArrayList<rated>();
        Date d=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String strdate = sdf.format(d);



        File file = new File("E:\\数据\\七分甜");
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

                        if(!"评论".equals(file2.getName().split("_")[0]))
                        {
                            List<String> l=new ArrayList<String>();
                            List<String> lpl=new ArrayList<String>();
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
                            FileReader readerpl = new FileReader("E:\\数据\\七分甜\\评论_"+file2.getName());
                            BufferedReader brpl = new BufferedReader(readerpl); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                            String linepl;

                            while ((linepl = brpl.readLine()) != null) {
                                // 一次读入一行数据

                                lpl.add(linepl);
                            }
                            //存放商家的信息
                            ele_Shop ele_shop=new ele_Shop();
                            Object plzs= FastJsonUtils.textToJson(lpl.get(0).toString());
                            Map mplzs= FastJsonUtils.textTomap(plzs.toString());
                            List<Map> listplzs=(List<Map>)mplzs.get("tags");
                            int  commentscount=0;
                            int  good=0;
                            int  bad=0;
                            int  picture=0;
                            int  tastesgood=0;
                            for(int pzindex=0;pzindex<listplzs.size();pzindex++)
                            {
                                if(pzindex==0)
                                {
                                    //评论总数
                                       commentscount= Integer.parseInt(listplzs.get(pzindex).get("count").toString());
                                }
                                if(pzindex==2)
                                {
                                    //好评
                                       good= Integer.parseInt(listplzs.get(pzindex).get("count").toString());
                                }
                                if(pzindex==3)
                                {
                                    //差评
                                       bad= Integer.parseInt(listplzs.get(pzindex).get("count").toString());
                                }
                                if(pzindex==4)
                                {
                                    //有图
                                       picture= Integer.parseInt(listplzs.get(pzindex).get("count").toString());
                                }
                                if(pzindex==5)
                                {
                                    //味道好
                                       tastesgood= Integer.parseInt(listplzs.get(pzindex).get("count").toString());
                                }

                            }





                            ele_shop.setCommentscount(commentscount);
                            ele_shop.setGood(good);
                            ele_shop.setBad(bad);
                            ele_shop.setPicture(picture);
                            ele_shop.setTastesgood(tastesgood);
                            Object dp= FastJsonUtils.textToJson(l.get(0).toString());
                            Map dpmap= FastJsonUtils.textTomap(dp.toString());
                            //拿地址
                            Map rst=(Map) dpmap.get("rst");
                            String srst=rst.get("address").toString();
                            //拿店名
                            String sname=rst.get("name").toString();

                            //拿纬度
                            String slat=rst.get("latitude").toString();
                            //拿经度
                            String slon=rst.get("longitude").toString();
                            //拿电话
                            String sphone=rst.get("phone").toString();
                            //获取唯一主键
                            String id=CREATE_UUID.getId();
                            //拿店铺的月销量
                            int recent_order_num=Integer.parseInt(rst.get("recent_order_num").toString());
                            //拿店铺的评分
                            double shoprating=Double.parseDouble(rst.get("rating").toString());
                            //区域
                            String area="";

                            for(int index=2;index<file2.getName().split("_").length;index++)
                            {
                                area +=file2.getName().split("_")[index];
                            }

                            ele_shop.setAddress(srst);
                            ele_shop.setArea(area);
                            ele_shop.setBrandid("938af33e1e6b46de8e33bc9b6042efbb");
                            ele_shop.setId(id);
                            ele_shop.setName(sname);
                            ele_shop.setLat(slat);
                            ele_shop.setLon(slon);
                            ele_shop.setPhone(sphone);
                            ele_shop.setShoprating(shoprating);
                            ele_shop.setRecentordernum(recent_order_num);
                            ele_shop.setInsertdata(strdate);
                            ele_shop.setPicture((int)(10+Math.random()*(19-10)));
                            listshop.add(ele_shop);

                            //开始拿吃的
                            List<Map> menu=(List<Map>)dpmap.get("menu");
                            for(int k=0;k<menu.size();k++)
                            {
                                //拿食品类型
                                String typename=menu.get(k).get("name").toString();
                                //拿到吃的集合
                                List<Map> foods=(List<Map>)menu.get(k).get("foods");
                                for(int p=0;p<foods.size();p++)
                                {
                                    //拿吃的原料
                                    String description=foods.get(p).get("description").toString();
                                    //拿吃的名字
                                    String foodname=foods.get(p).get("name").toString();
                                    //拿吃的的月销量
                                    int  foodmonth_sales=Integer.parseInt(foods.get(p).get("month_sales").toString());
                                    //拿吃的评分
                                    double foodrating=Double.parseDouble(foods.get(p).get("rating").toString());
                                    //拿吃的热度
                                    double satisfy_rate=Double.parseDouble(foods.get(p).get("satisfy_rate").toString());
                                    //单品最低价
                                    int lowest_price=(int) Double.parseDouble(foods.get(p).get("lowest_price").toString());
                                    System.out.println(lowest_price);
                                    ele_Food ele_food=new ele_Food();
                                    ele_food.setDescription(description);
                                    ele_food.setFoodname(foodname);
                                    ele_food.setFoodrating(foodrating);
                                    ele_food.setSatisfy_rate(satisfy_rate);
                                    ele_food.setLowest_price(lowest_price);
                                    ele_food.setFoodmonth_sales(foodmonth_sales);
                                    ele_food.setInsertdata(strdate);
                                    ele_food.setFoodtype(typename);
                                    ele_food.setShopid(id);
                                    listfood.add(ele_food);


                                }

                            }



                            Object o= FastJsonUtils.textToJson(lpl.get(0).toString());
                            Map m= FastJsonUtils.textTomap(o.toString());
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
                                String food_image_names="";

                                Map avater=FastJsonUtils.textTomap(lo.toString());
                                // 日期
                                rated_at=avater.get("rated_at").toString();
                                //拿到用户名称
                                username=avater.get("username").toString();
                                //评论内容
                                rating_text=avater.get("rating_text").toString();
                                //评分
                                rating=avater.get("rating").toString();
                                //拿吃的
                                List<Map> food_ratings=(List<Map>)avater.get("food_ratings");
                                String sfood="";
                                for(int i=0;i<food_ratings.size();i++)
                                {


                                    if(i==food_ratings.size()-1)
                                    {
                                        sfood+=food_ratings.get(i).get("rate_name").toString();
                                    }
                                    else
                                    {
                                        sfood +=food_ratings.get(i).get("rate_name").toString()+",";
                                    }

                                }
                                r.setFood_names(sfood);
                                //拿吃的和图片
                                List<Map> order_images=(List<Map>)avater.get("order_images");
                                if(order_images!=null)
                                {
                                    order_images.get(0).get("image_hash");
                                    //List order_imagesmap=(List)order_images.get(0).toString();
                                    // List listorder_images=(List) order_images.get(0).toString();
                                    image= order_images.get(0).get("image_hash").toString();
                                    food_image_names= order_images.get(0).get("food_names").toString().replace("\"]","").replace("[\"","").replace("\"","");
                                }
                                System.out.println(image);
                                System.out.println(avater);
                                r.setRated_at(rated_at);
                                r.setUsername(username);
                                r.setRating_text(rating_text);
                                r.setRating(rating);
                                r.setFood_image_names(food_image_names);
                                r.setImage_hash(image);
                                r.setShopname(file2.getName().split("_")[0].trim());
                                r.setShopid(id);
                                r.setInsertdata(strdate);
                                lwtfile.add(r);

                            }


                        }
                    }
                }
                System.out.println(lwtfile);
                System.out.println(listfood);
                System.out.println(listshop);
                for(rated r:lwtfile)
                {
                    System.out.println(r);
                }

                MongoClient mongoClient = null;
                try {
                    mongoClient = new MongoClient("127.0.0.1", 27017);
                    MongoDatabase mongoDatabase = mongoClient.getDatabase("WXYJ_CY");
                    System.out.println("Connect to database successfully");
                    MongoCollection<Document> collection = mongoDatabase
                            .getCollection("cy_ele_Rated");
                    MongoCollection<Document> collectionshop = mongoDatabase
                            .getCollection("cy_ele_Shop");
                    MongoCollection<Document> collectionfood = mongoDatabase
                            .getCollection("cy_ele_Food");
                    // insert(collection);
                    FindIterable<Document> findIterable = collection.find();
                    MongoCursor<Document> mongoCursor = findIterable.iterator();
                    while (mongoCursor.hasNext()) {
                        System.out.println(mongoCursor.next());
                    }
                    for(rated r:lwtfile)
                    {
                        Document document = new Document();
                        document.put("food_image_names",r.getFood_image_names());
                        document.put("image_hash", r.getImage_hash());
                        document.put("rating", r.getRating());
                        document.put("rated_at",r.getRated_at());
                        document.put("rating_text",r.getRating_text());
                        document.put("username", r.getUsername());
                        document.put("content", r.getContent());
                        document.put("created_at",r.getCreated_at());
                        document.put("shopname", r.getShopname());
                        document.put("shopid", r.getShopid());
                        document.put("food_names", r.getFood_names());
                        document.put("insertdata", r.getInsertdata());
                        collection.insertOne(document);
                    }
                    for(ele_Food f:listfood)
                    {
                        Document document = new Document();
                        document.put("description",f.getDescription());
                        document.put("foodname", f.getFoodname());
                        document.put("foodmonth_sales",f.getFoodmonth_sales());
                        document.put("foodrating",f.getFoodrating());
                        document.put("satisfy_rate",f.getSatisfy_rate());
                        document.put("lowest_price",f.getLowest_price());
                        document.put("insertdata", f.getInsertdata());
                        document.put("foodtype",f.getFoodtype());
                        document.put("shopid", f.getShopid());
                        collectionfood.insertOne(document);
                    }

                    for(ele_Shop s:listshop)
                    {
                        Document document = new Document();
                        document.put("address",s.getAddress());
                        document.put("name",s.getName());
                        document.put("lat",s.getLat());
                        document.put("lon",s.getLon());
                        document.put("phone",s.getPhone());
                        document.put("id",s.getId());
                        document.put("brandid",s.getBrandid());
                        document.put("recentordernum",s.getRecentordernum());
                        document.put("shoprating",s.getShoprating());
                        document.put("area",s.getArea());
                        document.put("insertdata",s.getInsertdata());
                        document.put("commentscount",s.getCommentscount());
                        document.put("good",s.getGood());
                        document.put("bad",s.getBad());
                        document.put("picture",s.getPicture());
                        document.put("tastesgood",s.getTastesgood());
                        collectionshop.insertOne(document);
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
