package com.hw.cy;

import com.hw.cy.entity.*;
import com.hw.cy.other.IPBean;
import com.hw.cy.other.IPList;
import com.hw.cy.pojo.geographicalPosition;
import com.hw.cy.util.HttpUtils;
import com.mao.spider.FastJsonUtils;
import com.sun.javafx.collections.MappingChange;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.poi.util.SystemOutLogger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class elesjb {
    private static final String h1="0.8654670624412151";
    private static final String h1user="136129407";
    private static final String h2= "1121690450";
    private static final String h1user2="0.9948449986911576";
    private static  final String COOKIE3="";
    private static  final String COOKIE4="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; USERID=1121690450; UTUSER=1121690450; SID=auwoIjm8gl2vza6ilEqXG9AICZbaUdcJm9Zg; ZDS=1.0|1565162712|zOCEjmUZim7JOWyvj5mr3tKdThiXSRucGyrkrEu318SdB3nolQf+vtx5Cqc+5NIl; l=cBjITp7RqR6oYr_yBOfanurza779UIRb8sPzaNbMiICPOIC9Bfh5WZFw1G8pCnGVLsQvR3rCLlQLByTLsPathsDMuWKaKoMN.; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1EXFJKVuMv-EXI01FlsTQfIldt0z5T-EFAOcLg9wfYzC; isg=BPX1pliaxP52JiBPDw0RAibSBHGvcqmEY9XKQ3caYmy7T";
    private static  final String X="118#ZVWZza9fMVIPmZVQJzkDghVdfFZDZ+ZBqhzpPYZgYGIzFZW72gsmGfmBIkcXOSA45DoXuAkCFmMIHasgQZuYPAPQqZZy7R9eFEJfTw4WXlCHedGCR/63No7DTAeIqZnK7WUh3c0+3LKx/aUCHPEc6gli6w95iUYiCRQgucwA9yUuH1VNMPt+OhSz+LxhGZh0xzKq9lo4zOkcWbT4tpZ40Cw1n23D6IxALALWsX731QC76ysqmhdinUokdTsGufQZAHNgVftgdryNNmvugpPPVhyHK3Q5xWJJ+IZaaQME9tmIh3mCDrv9lOOo6GyEI0QbjYjFrcQjLFnecM7EHS7C3Fub0JKaIWll6mrAXqwhRSv5LeXW3vV6kA3tSL9J3JjGdpSgSexNWxdEGDB5CDoPW7A7ki2BFqq2Z5G6UZ60xsLMjZi97Lk5E7P4prLHiI0p0HpONeuqj0651lXXM/7+uW78oU/K6Hy48zvXEhGLzZWXc510hkACOj/NUqzQY/05WC06iUH==";

    private static final String MY_IP_API = "https://www.ipip.net/ip.html";
    private static  String pizza73686f7070696e67="";
    private static String cookie="";
    private static String cookie2="";
    private static List<IPBean> lip;
    private static  List<Map> listIP =new ArrayList<Map>();
    private static  int Ipindex;
    private static   int ipindex=0;
    private static  List<Map> listjc=new ArrayList<Map>();
    private  static String cookie3;
    public static void main(String[] args) throws Exception {
        listjc= get_ip_bs.get_ip_dl();
        if(listjc!=null)
        {
            listIP=listjc;
        }
        Ipindex=listIP.size()-1;

        //pizza73686f7070696e67=readfiletoke();
        pizza73686f7070696e67="_HHDoSEnvf0M2q6qepXm1FAyTZNy3tBde648eSCzSQO4g1S7dOBu02PWb6UC5q5R";
        cookie="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; " +
                "perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05;" +
                " cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05;" +
                " track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b;" +
                " tzyy=010bf680a7183b114a800879e17142d4; " +
                "perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; l=cBjITp7RqR6oY9GOBOCwourza77OSIRAguPzaNbMi_5QA6T_cDbOk5fexF96VjWd97YB4k6UXwp9-etlw2tvDmCPv4Nd.;" +
                " FAAS_GRAY_ID=99; USERID=1121690450; UTUSER=1121690450; SID=ygaSBFgubNyx8eXFJckWnpD9EWvkAWSKYTZg;" +
                " ZDS=1.0|1565331435|VpThzDrzhYiVwKOvCPB/CRh7rG//CH2O4l2ubzwLxK0Am8CpyvSvvtzxA6Ac5RZs; " +
                "pizza73686f7070696e67="+pizza73686f7070696e67+"; " +
                "isg=BAYG6xXKp1xeJ3MyyFgShzlPV_yIZ0ohuW1qh_Av8yks86cNW_WGM-HFz2ma20I5";
        cookie2="ubt_ssid=86sqm65vlg8zwj6wahyzh1lvxbkew1tb_2019-08-12; perf_ssid=lpoafirnur2niha7t3vgm95ya4bx0nsu_2019-08-12; " +
                "ut_ubt_ssid=ff4wgafxu0u78mt7clomxtdc6ovgtiza_2019-08-12; _bl_uid=5Lj9nz0d81X3LOfURiFaqyef6Lz7; cna=XgfYFaoHe2gCAdoElg4igZ9/;" +
                " _utrace=ad94300019b4e37e8bd3d841594030aa_2019-08-12; track_id=1565596050|e005ea16c551ad959d1075c71e530add6a18ca11104550e0ae|23e9e8a99521c718a2c1cbf79fb701d7; " +
                "tzyy=010bf680a7183b114a800879e17142d4; _uab_collina=156559912638835530770661; USERID=1121690450; UTUSER=1121690450; SID=BWjMEU0pUnGVJGQFjDfZxkKmXD2KwF3UFGwg;" +
                " ZDS=1.0|1565603955|uEae4eCgLK3WX++2yenpPIs13cz022bWuPODLTTYLYIN5Z8D+sx5awr/TAbcfGvG; " +
                "pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1FAyTZNy3tBde648eSCzSQO4g1S7dOBu02PWb6UC5q5R;" +
                " isg=BAkJYUL0sOnvh0wL8-nFvpKOGDVjVv2Iwow1Hqt-hPAv8igE86Z1WTIkMBBhqpXA";
        System.out.println(lip);
        Map<String,String> wrmap=new HashMap<String,String>();
        Set<String> checklog=new HashSet<String>();
        checklog=readfile();
        try {
            Set eleidset=new HashSet();
            List<geographicalPosition> list=jxzb.getcs();
            for(geographicalPosition p:list)
            {
                if("苏州市".equals(p.getCityname()))
                {
                    if(checklog.add(p.getssqj()))
                    {
                        Map<String,String> mapcs=p.getlnglat();
                        String latitude_=mapcs.get("lat");
                        String longitude_=mapcs.get("lng");
                        List<Map> lm=getzuobiao("https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword="+URLzh.getURLEncoderString(p.getssqj())+"&offset=0&limit=20&latitude="+latitude_+"&longitude="+longitude_,longitude_,latitude_,listIP.get(ipindex));

                        for(int i=0;i<lm.size();i++)
                        {
                            if(i==1)
                            {
                                break;
                            }

                            if(checklog.add(lm.get(i).get("address").toString()))
                            {
                                BigDecimal longitude=(BigDecimal)lm.get(i).get("longitude");
                                BigDecimal latitude=(BigDecimal)lm.get(i).get("latitude");

                                Map mapdianpu =getdianmingID("https://h5.ele.me/restapi/shopping/v2/restaurants/search?offset=0&limit=30&keyword="+ URLzh.getURLEncoderString("蜜雪冰城")+"&latitude="+latitude+"&longitude="+longitude+"&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5&order_by=11",longitude,latitude,listIP.get(ipindex));

                           //     Map mapdianpu =getdianmingID("https://h5.ele.me/restapi/shopping/v2/restaurants/search?offset=0&limit=15&keyword=%E9%BA%BB%E8%BE%A3%E7%83%AB&latitude=31.275658&longitude=120.528033&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5&order_by=11",longitude,latitude,listIP.get(ipindex));

                                System.out.println(mapdianpu);
                                Map insidelist=(Map) mapdianpu.get("inside");

                                if(insidelist.containsKey("0"))
                                {
                                    //准备获取店家
                                    Map map_restaurant_with_foods=(Map)insidelist.get("0");
                                    //店家id集合
                                    List djlist=(List)map_restaurant_with_foods.get("restaurant_with_foods");
                                    for(int j=0;j< djlist.size();j++)
                                    {
                                        Map onemapxx=(Map) djlist.get(j);
                                        Map map_name_id=(Map) onemapxx.get("restaurant");
                                        String id= map_name_id.get("id").toString();
                                        String name=map_name_id.get("name").toString();
                                        String average_cost="¥1人";
                                        if(map_name_id.containsKey("average_cost")&&map_name_id.get("average_cost")!=null)
                                        {
                                             average_cost=map_name_id.get("average_cost").toString().replace("/","");
                                        }

                                        //检查本地有没有获取过
                                        if(checklog.add(name))
                                        {
                                            //检查当前程序有没有获取过
                                            if(eleidset.add(id)){

                                                BigDecimal _latitude=(BigDecimal)map_name_id.get("latitude");
                                                BigDecimal _longitude=(BigDecimal)map_name_id.get("longitude");
                                                //拿到名字和id，发送获取数据的请求
                                                //https://h5.ele.me/pizza/shopping/restaurants/E8826911105091264178/batch_shop?user_id=1121690450&code=0.5003637895149904&extras=%5B%22
                                                //           activities%22%2C%22albums%22%2C%22license%22%2C%22identification%22%2C%22qualification%22%5D&terminal=h5&latitude=31.312356&longitude=120.601762
                                                Map  sj=getdianshuju("https://h5.ele.me/pizza/shopping/restaurants/"+id+"/batch_shop?user_id="+h2+"&code="+h1user2+"&extras=%5B%22" +
                                                        "activities%22%2C%22albums%22%2C%22license%22%2C%22identification%22%2C%22qualification%22%5D&terminal=h5&latitude="+_latitude+"&longitude="+_longitude,id,_longitude,_latitude,listIP.get(ipindex));

                                                String pl=getdianpinglun("https://h5.ele.me/pizza/ugc/restaurants/"+id+"/batch_comments?has_content=true&offset=0&limit=20",id,_longitude,_latitude,listIP.get(ipindex));
                                                String s = FastJsonUtils.convertObjectToJSON(sj);
                                                //   writefile.writer( name, id,p.getProvincename(),p.getCityname(),p.getDistrictname(),s);
                                                // writefile.writer( "评论_"+name, id,p.getProvincename(),p.getCityname(),p.getDistrictname(),pl);
                                                System.out.println("写入成功"+name);
                                                //先放集合
                                                wrmap.put(name+"_"+id+"_"+average_cost+"_"+p.getProvincename()+"_"+p.getCityname()+"_"+p.getDistrictname(),s);
                                                wrmap.put("评论_"+name+"_"+id+"_"+average_cost+"_"+p.getProvincename()+"_"+p.getCityname()+"_"+p.getDistrictname(),pl);
                                                //保存日志
                                                checklog.add(name);

                                            }
                                            else{
                                                System.out.println("保存过了"+name);
                                            }
                                        }


                                    }
                                }
                                System.out.println("遍历过的地址："+lm.get(i).get("address").toString());
                                //记录遍历过的地址
                                checklog.add(lm.get(i).get("address").toString());

                            }

                        }
                        System.out.println("查询过的区域："+p.getssqj());
                        //记录查询过的区域日志
                        checklog.add(p.getssqj());
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("----------------被踢下线，保存数据----------");

        }
        finally {
            System.out.println("------------开始保存数据");

            for(Map.Entry<String, String> vo : wrmap.entrySet()){
                vo.getKey();
                vo.getValue();
                //    System.out.println(vo.getKey()+" "+vo.getValue());
                writefile.writersj(vo.getKey(),vo.getValue());
            }
            System.out.println("------------开始保存日志");
            for (String s:checklog)
            {
                writefile.writerrz(s);

            }
            System.out.println("------------开始保存令牌");

            writefile.writertoke(pizza73686f7070696e67);
        }



    }

    //设置城市为苏州，拿到收获地址的坐标
    public static List<Map> getzuobiao(String url,String _longitude,String _latitude,Map map) throws Exception {
//        int ipindex=(int)(0+Math.random()*(lip.size()-1));
        Thread.sleep((long) (1000+Math.random()*(4000-1000)));
        System.out.println(url);
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {
            //代理对象
            //HttpHost proxy = new HttpHost(lip.get(ipindex).getIp(), lip.get(ipindex).getPort(), "http");
            HttpHost proxy = new HttpHost(map.get("ip").toString(),Integer.parseInt(map.get("port").toString()),"http");
            //配置对象
            System.out.println(map.get("ip").toString()+ map.get("port").toString());
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

//            client = HttpClients.createDefault();
            client=createSSLClientDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(config);
//            httpGet.addHeader("x-forwarded-for",map.get("ip").toString());
//            httpGet.setHeader(":authority","h5.ele.me");
//            httpGet.setHeader(":method","GET");
//            httpGet.setHeader(":path",url.substring(17));
//            httpGet.setHeader(":scheme", "https");
//            httpGet.setHeader("accept", "*/*");
//            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
//            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
//            httpGet.setHeader("cookie",cookie2);
            httpGet.setHeader("user-agent", elesj.getuseragent());
//            httpGet.setHeader("referer", "https://h5.ele.me/msite/");
//            httpGet.setHeader("x-shard", "loc="+_longitude+","+_latitude);

            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("换地址网页内容:"+result);
            List<Map> maps = FastJsonUtils.toList(result, Map.class);

            ipindex++;
            if(ipindex>=Ipindex)
            {
                Thread.sleep(10000);
                ipindex=0;
                listjc= get_ip_bs.get_ip_dl();
                if(listjc!=null)
                {
                    listIP=listjc;
                }
                Ipindex=listIP.size()-1;

            }

            return maps;
        } catch (Exception e) {

            System.out.println(url);
            System.out.println(_longitude);
            System.out.println(_latitude);
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
    //在搜索框输入店名
    public static Map getdianmingID(String url, BigDecimal longitude,BigDecimal latitude,Map map) throws Exception {
        //    int ipindex=(int)(0+Math.random()*(lip.size()-1));
        Thread.sleep((long) (1000+Math.random()*(4000-1000)));
        System.out.println(url);
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        HttpHost proxy = new HttpHost(map.get("ip").toString(),Integer.parseInt(map.get("port").toString()),"http");
        System.out.println(map.get("ip").toString()+ map.get("port").toString());
        //配置对象
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        try {

            client=createSSLClientDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("x-forwarded-for",map.get("ip").toString());
            httpGet.setConfig(config);
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
//            httpGet.setHeader("cookie", "ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; FAAS_GRAY_ID=99; USERID=235345536; UTUSER=235345536; SID=QvnaJnjOgYCUY3nlVGQlcjeC90lcxMqpwDgQ; ZDS=1.0|1565137176|U3tynouC6eCCEnMUujAm0j1tg8M6sMV2SsEP7HRVtcFW7FAxBaEMkOU9YG/nA1B+; l=cBjITp7RqR6oYD_-BOfN-urza77OEQOf1sPzaNbMiICP9O1pVWllWZFZjNL9CnGVLsKk-3rCLlQLBWLsRy4EQ2hdKVXnveVV.; pizza7567632f76332f72=5xMqRQuOWng_3Lh8jjs5aFxKpGiBtIc4fL-03KN3y1MMEPyj0IUHidguhGp6dTBI; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1F9xDggUoM-w1NghvI12nFc3q78CXKJ-dglFuN8wrtrf; isg=BFlZfFQZYILT_TwbA3l1rmIeaEUz5k2Y-IBO5nsO-AD_gnoUwDSNa4_UgABROuXQ");
            httpGet.setHeader("cookie",cookie2);
            httpGet.setHeader("referer","https://h5.ele.me/search/");
//            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            httpGet.setHeader("user-agent", elesj.getuseragent());
//            upgrade-insecure-requests: 1
            httpGet.setHeader("x-shard", "loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("输入店名网页内容:"+result);
            Map maps = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);

            ipindex++;
            if(ipindex>=Ipindex)
            {
                Thread.sleep(10000);
                ipindex=0;
                listjc= get_ip_bs.get_ip_dl();
                if(listjc!=null)
                {
                    listIP=listjc;
                }
                Ipindex=listIP.size()-1;

            }

            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
    //拿掉店的id之后，拿店的数据
    public static Map getdianshuju(String url, String id, BigDecimal longitude,BigDecimal latitude,Map map) throws Exception {
        //  int ipindex=(int)(0+Math.random()*(lip.size()-1));
        Thread.sleep((long) (1000+Math.random()*(4000-1000)));
        System.out.println(url);
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        HttpHost proxy = new HttpHost(map.get("ip").toString(),Integer.parseInt(map.get("port").toString()),"http");
        System.out.println(map.get("ip").toString()+ map.get("port").toString());
        //配置对象
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        try {

            client = createSSLClientDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(config);
            httpGet.addHeader("x-forwarded-for",map.get("ip").toString());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cache-control","max-age=0");
            httpGet.setHeader("cookie",cookie2);
            httpGet.setHeader("referer","https://h5.ele.me/shop/");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard", "shopid="+id+";loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("拿店名网页内容:"+result);
            //System.out.println("网页内容:"+result);
            Map maps = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);

            ipindex++;
            if(ipindex>=Ipindex)
            {
                Thread.sleep(10000);
                ipindex=0;
                listjc= get_ip_bs.get_ip_dl();
                if(listjc!=null)
                {
                    listIP=listjc;
                }
                Ipindex=listIP.size()-1;

            }

            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
    //拿掉店的id之后，拿店的评论
    public static String getdianpinglun(String url, String id, BigDecimal longitude,BigDecimal latitude,Map map) throws Exception {
        Thread.sleep((long) (1000+Math.random()*(4000-1000)));
        System.out.println(url);
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        //  int ipindex=(int)(0+Math.random()*(lip.size()-1));

        HttpHost proxy = new HttpHost(map.get("ip").toString(),Integer.parseInt(map.get("port").toString()),"http");

        System.out.println(map.get("ip").toString()+ map.get("port").toString());
        //配置对象
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        try {

            client = createSSLClientDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(config);
            httpGet.setHeader("authority","h5.ele.me");
            httpGet.addHeader("x-forwarded-for",map.get("ip").toString());
            httpGet.setHeader("method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader(":accept","application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie",cookie2);
            httpGet.setHeader("referer","https://h5.ele.me/search/");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard","shopid="+id+";loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("拿评论网页内容:"+result);
//                Map map = (Map) FastJsonUtils.convertJsonToObject(result,Map.class);
            //              System.out.println(map);
            Header header[]=response.getAllHeaders();
            System.out.println(header);
            System.out.println(header[5].toString().split(";")[0].split(":")[1]);
            //System.out.println("网页内容:"+result);


            if("pizza73686f7070696e67".equals(header[5].toString().split(";")[0].split(":")[1].toString().split("=")[0]))
            {
                String setcook=header[5].toString().split(";")[0].split(":")[1].toString().split("=")[1];
                pizza73686f7070696e67= setcook;
                cookie2="ubt_ssid=86sqm65vlg8zwj6wahyzh1lvxbkew1tb_2019-08-12; perf_ssid=lpoafirnur2niha7t3vgm95ya4bx0nsu_2019-08-12; ut_ubt_ssid=ff4wgafxu0u78mt7clomxtdc6ovgtiza_2019-08-12; _bl_uid=5Lj9nz0d81X3LOfURiFaqyef6Lz7; cna=XgfYFaoHe2gCAdoElg4igZ9/; _utrace=ad94300019b4e37e8bd3d841594030aa_2019-08-12; track_id=1565596050|e005ea16c551ad959d1075c71e530add6a18ca11104550e0ae|23e9e8a99521c718a2c1cbf79fb701d7; USERID="+h2+"; UTUSER="+h2+"; SID=5fDtgsBr5IhmIfvK6Ss2qFDUlHCiSUlWHg3A; ZDS=1.0|1565596050|uD/CEfO4P+IuZpcjhAgV/LTwkvDq9lm/EtvG3PDZVgCd+BKn6g+IFFmj3CLCXTxF; tzyy=010bf680a7183b114a800879e17142d4; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1Owd_b7leNnpTxexKkdr5A27bLCvbstt9NW0xL_ybVJ_;" +
                        " pizza7567632f76332f72="+pizza73686f7070696e67+"; isg=BJubqAVXQnOFQL6Rlc9XVKywKv8FcK9yDEJnJI3YdhqxbLpOFUHBwZOqAorHzAdq";
            }

            System.out.println(pizza73686f7070696e67);
            System.out.println(cookie2);

            ipindex++;
            if(ipindex>=Ipindex)
            {
                Thread.sleep(10000);
                ipindex=0;
                listjc= get_ip_bs.get_ip_dl();
                if(listjc!=null)
                {
                    listIP=listjc;
                }
                Ipindex=listIP.size()-1;

            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }

    }

    //拿掉店的id之后，拿店的评论
    public static String getdianpinglunWIN(String url, String id, BigDecimal longitude,BigDecimal latitude) throws Exception {
        Thread.sleep((long) (1000+Math.random()*(6000)));
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        int ipindex=(int)(0+Math.random()*(lip.size()-1));
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", lip.get(ipindex).getPort()+"");
        System.setProperty("proxyHost",lip.get(ipindex).getIp());
        System.setProperty("proxySet", "true");
        try {

            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder("https://www.ele.me/restapi/ugc/v1/restaurant/"+id+"/ratings?limit=100&offset=0&record_type=1");
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("authority","h5.ele.me");
            httpGet.setHeader("method","GET");
            httpGet.setHeader("path", "/pizza/ugc/restaurants/"+id+"/batch_comments?has_content=true&offset=0&limit=2000");
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader(":accept","application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", cookie);
            httpGet.setHeader("referer","https://h5.ele.me/shop/");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard","shopid="+id+";loc="+longitude+","+latitude);
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("网页内容:"+result);
//                Map map = (Map) FastJsonUtils.convertJsonToObject(result,Map.class);
            //              System.out.println(map);
            Header header[]=response.getAllHeaders();
            System.out.println(header);
            System.out.println(header[5].toString().split(";")[0].split(":")[1]);
            //System.out.println("网页内容:"+result);

            if("pizza73686f7070696e67".equals(header[5].toString().split(";")[0].split(":")[1].toString().split("=")[0]))
            {
                String setcook=header[5].toString().split(";")[0].split(":")[1].toString().split("=")[1];
                pizza73686f7070696e67= setcook;
                cookie2="ubt_ssid=86sqm65vlg8zwj6wahyzh1lvxbkew1tb_2019-08-12; perf_ssid=lpoafirnur2niha7t3vgm95ya4bx0nsu_2019-08-12; ut_ubt_ssid=ff4wgafxu0u78mt7clomxtdc6ovgtiza_2019-08-12; _bl_uid=5Lj9nz0d81X3LOfURiFaqyef6Lz7; cna=XgfYFaoHe2gCAdoElg4igZ9/; _utrace=ad94300019b4e37e8bd3d841594030aa_2019-08-12; track_id=1565596050|e005ea16c551ad959d1075c71e530add6a18ca11104550e0ae|23e9e8a99521c718a2c1cbf79fb701d7; USERID="+h2+"; UTUSER=235345536; SID=5fDtgsBr5IhmIfvK6Ss2qFDUlHCiSUlWHg3A; ZDS=1.0|1565596050|uD/CEfO4P+IuZpcjhAgV/LTwkvDq9lm/EtvG3PDZVgCd+BKn6g+IFFmj3CLCXTxF; tzyy=010bf680a7183b114a800879e17142d4; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1Owd_b7leNnpTxexKkdr5A27bLCvbstt9NW0xL_ybVJ_;" +
                        " pizza7567632f76332f72="+pizza73686f7070696e67+"; isg=BJubqAVXQnOFQL6Rlc9XVKywKv8FcK9yDEJnJI3YdhqxbLpOFUHBwZOqAorHzAdq";
            }


            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }

    }
    private static Set<String> readfile() throws Exception
    {
        Set l=new HashSet();
        String pathname = "E:\\812\\日志"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
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
        return l;
    }
    private static String readfiletoke() throws Exception
    {
        String toke="";
        String pathname = "E:\\令牌"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        FileReader reader = new FileReader(pathname);
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line;

        while ((line = br.readLine()) != null) {
            // 一次读入一行数据

            toke=line;
        }
        return toke;

    }


    // 获取当前ip地址，判断是否代理成功
    public static String getMyIp() {
        try {
            String html = HttpUtils.getResponseContent(MY_IP_API);

            Document doc = Jsoup.parse(html);
            Element element = doc.selectFirst("div.tableNormal");

            Element ele = element.selectFirst("table").select("td").get(1);

            String ip = element.selectFirst("a").text();

            // System.out.println(ip);
            return ip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy(){

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static String getcookie( Map map) throws Exception
    {


        String url="https://h5.ele.me/pizza/ugc/restaurants/E16878222478688774550/batch_comments?has_content=true&offset=0&limit=20";
        Thread.sleep((long) (1000+Math.random()*(4000-1000)));
        System.out.println(url);
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        //  int ipindex=(int)(0+Math.random()*(lip.size()-1));

        HttpHost proxy = new HttpHost(map.get("ip").toString(),Integer.parseInt(map.get("port").toString()),"http");

        System.out.println(map.get("ip").toString()+ map.get("port").toString());
        //配置对象
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

        try {

            client = createSSLClientDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(config);
            httpGet.setHeader("authority","h5.ele.me");
            httpGet.addHeader("x-forwarded-for",map.get("ip").toString());
            httpGet.setHeader("method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader(":accept","application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie",cookie2);
            httpGet.setHeader("referer","https://h5.ele.me/search/");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard","loc=120.528033,31.275658");
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("获取pizza73686f7070696e67");
//                Map map = (Map) FastJsonUtils.convertJsonToObject(result,Map.class);
            //              System.out.println(map);
            Header header[]=response.getAllHeaders();
            System.out.println(header);
            System.out.println(header[5].toString().split(";")[0].split(":")[1]);
            //System.out.println("网页内容:"+result);


            if("pizza73686f7070696e67".equals(header[5].toString().split(";")[0].split(":")[1].toString().split("=")[0]))
            {
                String setcook=header[5].toString().split(";")[0].split(":")[1].toString().split("=")[1];
                pizza73686f7070696e67= setcook;
                cookie2="ubt_ssid=86sqm65vlg8zwj6wahyzh1lvxbkew1tb_2019-08-12; perf_ssid=lpoafirnur2niha7t3vgm95ya4bx0nsu_2019-08-12; ut_ubt_ssid=ff4wgafxu0u78mt7clomxtdc6ovgtiza_2019-08-12; _bl_uid=5Lj9nz0d81X3LOfURiFaqyef6Lz7; cna=XgfYFaoHe2gCAdoElg4igZ9/; _utrace=ad94300019b4e37e8bd3d841594030aa_2019-08-12; track_id=1565596050|e005ea16c551ad959d1075c71e530add6a18ca11104550e0ae|23e9e8a99521c718a2c1cbf79fb701d7; USERID=235345536; UTUSER=235345536; SID=5fDtgsBr5IhmIfvK6Ss2qFDUlHCiSUlWHg3A; ZDS=1.0|1565596050|uD/CEfO4P+IuZpcjhAgV/LTwkvDq9lm/EtvG3PDZVgCd+BKn6g+IFFmj3CLCXTxF; tzyy=010bf680a7183b114a800879e17142d4; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1Owd_b7leNnpTxexKkdr5A27bLCvbstt9NW0xL_ybVJ_;" +
                        " pizza7567632f76332f72="+pizza73686f7070696e67+"; isg=BJubqAVXQnOFQL6Rlc9XVKywKv8FcK9yDEJnJI3YdhqxbLpOFUHBwZOqAorHzAdq";
            }

            System.out.println(pizza73686f7070696e67);
            System.out.println(cookie2);

            ipindex++;
            if(ipindex>=Ipindex)
            {
                Thread.sleep(10000);
                ipindex=0;
                listjc= get_ip_bs.get_ip_dl();
                if(listjc!=null)
                {
                    listIP=listjc;
                }
                Ipindex=listIP.size()-1;

            }

            return cookie2;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }

}
