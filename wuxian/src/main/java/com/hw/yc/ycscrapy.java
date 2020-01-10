package com.hw.yc;

import com.alibaba.fastjson.JSON;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.StringUtils;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ycscrapy {
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
                    Map map=new HashMap();
                    //System.out.println("地址"+ links.get(j).toString());
                    Document d = Jsoup.parse(links.get(j).toString());
                     String cd = Jsoup.parse(linkss.get(j).toString()).text();
                     map.put("place_of_origind",cd);
                    Elements e = d.select("a[href]");
                    e.attr("href");
                    System.out.println("地址" + e.attr("href"));
                    String ycid=e.attr("href").toString().split("=")[1];


                    CloseableHttpClient client2 = null;
                    CloseableHttpResponse response2 = null;
                    client2 = HttpClients.createDefault();
                    URIBuilder uriBuilder2 = new URIBuilder(infolink+e.attr("href"));
                    HttpGet httpGet2 = new HttpGet(uriBuilder2.build());
                    response2 = client2.execute(httpGet2);
                    HttpEntity entity2 = response2.getEntity();
                    String result2 = EntityUtils.toString(entity2, "UTF-8");
                    // 创建saxReader对象
                    Document info = Jsoup.parse(result2);
                    //取产品简介
                    Element cpji=null;
                    cpji = info.getElementById("productsumm");
                     if(cpji!=null)
                     {

                         map.put("brief_introduction", cpji.text().replace("⚡ 以上产品信息来自品牌方或互联网公开资料，烟草市场网登载只为向合法成年烟草消费者传递产品信息，帮助消费者辨别产品真伪，不代表烟草市场网观点，也不作为对产品的推荐。烟草市场网提醒您，吸烟有害健康。",""));

                     }
                     else
                         map.put("brief_introduction","");
                    //取图片和名字
                    List<Element> imglist = info.getElementsByClass("col-4");
                    Elements lmgname = imglist.get(0).select("img[src]");
                    String ename = info.getElementsByTag("small").get(0).text() ;
                    List<Element> infolist = info.getElementsByClass("col-8");

                    Document zzinfo = Jsoup.parse(infolist.get(0).toString());
                    List<Element> zzinfolist = zzinfo.getElementsByClass("proBar proBarB proBar2");
                    List<Element> zzinfolist2 = zzinfo.getElementsByClass("proBar");

                    map.put("src",lmgname.attr("src"));
                    map.put("alt",lmgname.attr("alt"));
                    map.put("e_nmae",ename);
                    map.put("brand",lmgname.attr("alt").split("（")[0]);
                    map.put("ycid",ycid);
                    System.out.println(zzinfolist2.size());
                    if(zzinfolist2.size()==7||zzinfolist2.size()<7)
                    {
                        for (int y=0;y<zzinfolist2.size();y++)
                        {
//                        System.out.println(zzinfolist2.get(y).text().toString());
                            String string=zzinfolist2.get(y).text().toString();
                            if(y==0)
                            {
                                map.put("type",string.split("焦油量")[0].replace("产品类型：",""));

                                if(string.split("焦油量：").length!=1&&string.split("焦油量：").length!=0)
                                {
                                    map.put("tar",string.split("焦油量：")[1]);
                                }
                                else
                                    map.put("tar","");

                            }
                            if(y==1)
                            {
                                map.put("nicotine content",string.split("一氧化碳量")[0].replace("烟碱量：",""));
                                if(string.split("一氧化碳量：").length!=1 &&string.split("一氧化碳量：").length!=0 )
                                {
                                    map.put("carbon_monoxide_content",string.split("一氧化碳量：")[1]);
                                }
                                else
                                    map.put("carbon_monoxide_content","");



                            }
                            if(y==2)
                            {

                                map.put("packag_ingform",string.replace("包装形式：",""));

                            }
                            if(y==3)
                            {
                                map.put("cigarette_specification",string.replace("烟支规格：",""));

                            }
                            if(y==4)
                            {
                                map.put("smallbox_bar_code",string.split("条盒条码")[0].replace("小盒条码：",""));

                                if(string.split("条盒条码：").length!=1&&string.split("条盒条码：").length!=0 )
                                {
                                    map.put("bar_code",string.split("条盒条码：")[1]);
                                }
                                else
                                    map.put("bar_code","");


                            }
                            if(y==5)
                            {
                                map.put("retail_price_of_small_box",string.split("条盒零售价")[0].replace("小盒零售价：",""));

                                if(string.split("条盒零售价： ").length!=1 &&string.split("条盒零售价： ").length!=0 )
                                {
                                    map.put("retail_price_of_strip_and_box",string.split("条盒零售价：")[1]);
                                }
                                else
                                    map.put("retail_price_of_strip_and_box","");


                            }
                            if(y==6)
                            {
                                if(string.indexOf("上市时间")!=-1||string.indexOf("销售状态")!=-1)
                                {
                                    map.put("sales_status",string.split("销售状态")[0].replace("上市时间：",""));

                                    if(string.split("销售状态： ").length!=1 &&string.split("销售状态： ").length!=0 )
                                    {
                                        map.put("time_to_market",string.split("销售状态：")[1]);
                                    }
                                    else
                                        map.put("time_to_market","");
                                }else
                                map.put("wholesale_price",string.replace("批发价格：",""));

                            }

                        }
                    }
                    else
                    {
                        for (int y=0;y<zzinfolist2.size();y++)
                        {
//                        System.out.println(zzinfolist2.get(y).text().toString());
                            String string=zzinfolist2.get(y).text().toString();
                            if(y==0)
                            {
                                map.put("type",string.split("焦油量")[0].replace("产品类型：",""));

                                if(string.split("焦油量：").length!=1&&string.split("焦油量：").length!=0)
                                {
                                    map.put("tar",string.split("焦油量：")[1]);
                                }
                                else
                                    map.put("tar","");

                            }
                            if(y==1)
                            {
                                map.put("nicotine content",string.split("一氧化碳量")[0].replace("烟碱量：",""));
                                if(string.split("一氧化碳量：").length!=1 &&string.split("一氧化碳量：").length!=0 )
                                {
                                    map.put("carbon_monoxide_content",string.split("一氧化碳量：")[1]);
                                }
                                else
                                    map.put("carbon_monoxide_content","");



                            }
                            if(y==2)
                            {

                                map.put("packag_ingform",string.replace("包装形式：",""));

                            }
                            if(y==3)
                            {
                                map.put("cigarette_specification",string.replace("烟支规格：",""));

                            }
                            if(y==4)
                            {
                                map.put("smallbox_bar_code",string.split("条盒条码")[0].replace("小盒条码：",""));

                                if(string.split("条盒条码：").length!=1&&string.split("条盒条码：").length!=0 )
                                {
                                    map.put("bar_code",string.split("条盒条码：")[1]);
                                }
                                else
                                    map.put("bar_code","");


                            }
                            if(y==5)
                            {
                                map.put("retail_price_of_small_box",string.split("条盒零售价")[0].replace("小盒零售价：",""));

                                if(string.split("条盒零售价： ").length!=1 &&string.split("条盒零售价： ").length!=0 )
                                {
                                    map.put("retail_price_of_strip_and_box",string.split("条盒零售价：")[1]);
                                }
                                else
                                    map.put("retail_price_of_strip_and_box","");


                            }
                            if(y==6)
                            {
                                map.put("wholesale_price",string.replace("批发价格：",""));

                            }
                            if(y==7)
                            {
                                map.put("sales_status",string.split("销售状态")[0].replace("上市时间：",""));

                                if(string.split("销售状态：").length!=1 &&string.split("销售状态：").length!=0 )
                                {
                                    map.put("time_to_market",string.split("销售状态：")[1]);
                                }
                                else
                                    map.put("time_to_market","");



                            }

                        }

                    }

                    map.put("ycid",ycid);


                   Element pj= info.getElementById("oldPjs");
                   List<Element> l=info.getElementsByClass("proPj");
                    String sss=l.get(0).text().toString();
                    String []sz=sss.split("位网友评分结果");
                    map.put("number",sz[0]);
                    String sarray[] =sz[1].split("分 ");
                    for(int a=0;a<sarray.length;a++)
                    {
                        if(a==0)
                        {
                            map.put("flavor",sarray[a].split(": ")[1]);
                        }
                        if(a==1)
                        {
                            map.put("packing",sarray[a].split(": ")[1]);

                        }
                        if(a==2)
                        {

                            map.put("cost_performance",sarray[a].split(": ")[1]);

                        }
                        if(a==3)
                        {

                            map.put("comprehensive",sarray[a].split(": ")[1]);

                        }

                    }


                    System.out.println(map);
                    //取图片和名字
                    L.add( map);




                }







            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                response.close();
                client.close();
            }


        }
        L.stream().forEach(a->{
            MongoCollection<Map> collection = database.getCollection("tobaccos", Map.class);
            collection.insertOne((Map)a);
        });






    }


}


