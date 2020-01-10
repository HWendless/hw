package com.hw.cy;

import com.hw.cy.entity.*;
import com.hw.cy.other.IPBean;
import com.hw.cy.other.IPList;
import com.hw.cy.pojo.eleaddress;
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

public class elesjbip {
    private static final String h1="0.8654670624412151";
    private static final String h1user="136129407";
    private static final String h2= "235345536";
    private static final String h1user2="0.9531406581913082";
    private static final  String COOKIE="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; FAAS_GRAY_ID=99; USERID=235345536; UTUSER=235345536; SID=QvnaJnjOgYCUY3nlVGQlcjeC90lcxMqpwDgQ; ZDS=1.0|1565137176|U3tynouC6eCCEnMUujAm0j1tg8M6sMV2SsEP7HRVtcFW7FAxBaEMkOU9YG/nA1B+; l=cBjITp7RqR6oYD_-BOfN-urza77OEQOf1sPzaNbMiICP9O1pVWllWZFZjNL9CnGVLsKk-3rCLlQLBWLsRy4EQ2hdKVXnveVV.; pizza7567632f76332f72=5xMqRQuOWng_3Lh8jjs5aFxKpGiBtIc4fL-03KN3y1MMEPyj0IUHidguhGp6dTBI; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1KpDCi8rHxpMP1cCbGuSGxX2dlOZmN92wt_XdaMpz_kd; isg=BDMz4b6BapACJiZJPcdPHHQ4wjddaMcqTqY0lOXQjNKJ5FCGazl5eEcymlKvxB8i";
    private static final  String COOKIE2="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-10; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-10; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; USERID=235345536; l=cBjITp7RqR6oYD_-BOfN-urza77OEQOf1sPzaNbMiICP9O1pVWllWZFZjNL9CnGVLsKk-3rCLlQLBWLsRy4EQ2hdKVXnveVV.; pizza7567632f76332f72=5xMqRQuOWng_3Lh8jjs5aGUwYXKmR43RMg-IryS_m8ffaCIyZspHy3FLnt-sa0aO; UTUSER=235345536; SID=5HcSeUL5cKREmcbt0gYsrxLVbZzlVzK7NBtA; ZDS=1.0|1565162282|9tuPELNPb+c+GvKQOTRS5Dzev4IeF5Enw2e9p8HAxslGYLCm2SRhNmT+ptFgp9Xy; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1J8-r0faw9Q4y83ZHRLz842g1T-tZ1V2sJ5tg5DyQY1S; isg=BKOjkmcE-sAaYbb5LfffrGRoMudNmDfavnZExNUA-4J5FMc2Xmu_KxMCCqK_tI_S";
    private static  final String COOKIE3="";
    private static  final String COOKIE4="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; USERID=1121690450; UTUSER=1121690450; SID=auwoIjm8gl2vza6ilEqXG9AICZbaUdcJm9Zg; ZDS=1.0|1565162712|zOCEjmUZim7JOWyvj5mr3tKdThiXSRucGyrkrEu318SdB3nolQf+vtx5Cqc+5NIl; l=cBjITp7RqR6oYr_yBOfanurza779UIRb8sPzaNbMiICPOIC9Bfh5WZFw1G8pCnGVLsQvR3rCLlQLByTLsPathsDMuWKaKoMN.; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1EXFJKVuMv-EXI01FlsTQfIldt0z5T-EFAOcLg9wfYzC; isg=BPX1pliaxP52JiBPDw0RAibSBHGvcqmEY9XKQ3caYmy7T";
    private static  final String X="118#ZVWZza9fMVIPmZVQJzkDghVdfFZDZ+ZBqhzpPYZgYGIzFZW72gsmGfmBIkcXOSA45DoXuAkCFmMIHasgQZuYPAPQqZZy7R9eFEJfTw4WXlCHedGCR/63No7DTAeIqZnK7WUh3c0+3LKx/aUCHPEc6gli6w95iUYiCRQgucwA9yUuH1VNMPt+OhSz+LxhGZh0xzKq9lo4zOkcWbT4tpZ40Cw1n23D6IxALALWsX731QC76ysqmhdinUokdTsGufQZAHNgVftgdryNNmvugpPPVhyHK3Q5xWJJ+IZaaQME9tmIh3mCDrv9lOOo6GyEI0QbjYjFrcQjLFnecM7EHS7C3Fub0JKaIWll6mrAXqwhRSv5LeXW3vV6kA3tSL9J3JjGdpSgSexNWxdEGDB5CDoPW7A7ki2BFqq2Z5G6UZ60xsLMjZi97Lk5E7P4prLHiI0p0HpONeuqj0651lXXM/7+uW78oU/K6Hy48zvXEhGLzZWXc510hkACOj/NUqzQY/05WC06iUH==";

    private static final String MY_IP_API = "https://www.ipip.net/ip.html";
    private static String pizza73686f7070696e67="";
    private static String cookie="";
    private static String cookie2="";
    private static List<IPBean> lip;
    private static  List<Map> listIP =new ArrayList<Map>();
    private static  int Ipindex;
    private static int ipindex=0;
    private static  List<Map> listjc=new ArrayList<Map>();
    private  static String cookie3;
        public static void main(String[] args) throws Exception {
                    listjc= get_ip_bs.get_ip_dl();
                    if(listjc!=null)
                    {
                        listIP=listjc;
                    }
                    Ipindex=listIP.size()-1;

                System.out.println(lip);
            Map<String,String> wrmap=new HashMap<String,String>();
            Set<String> checklog=new HashSet<String>();
            checklog=readfile();
            try {
                Set eleidset=new HashSet();
                List<geographicalPosition> list=jxzb.getcs();
                for(geographicalPosition p:list)
                {
                    if("中华人民共和国".equals(p.getCountryname()))
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
//                                    eleaddress eleadd=new eleaddress();
                                    BigDecimal longitude=(BigDecimal)lm.get(i).get("longitude");
                                    BigDecimal latitude=(BigDecimal)lm.get(i).get("latitude");
                                    String address=lm.get(i).get("address").toString();
                                    String qy=p.getssqj();
//                                    eleadd.setAddress(address);
//                                    eleadd.setLatitude(latitude);
//                                    eleadd.setLongitude(longitude);
//                                    eleadd.setQy(qy);
                                    System.out.println(qy+","+address+","+longitude+","+latitude);
                                    FileWriter fw = new FileWriter("E:\\812\\饿了么地址",true);
                                    BufferedWriter bw = new BufferedWriter(fw);
                                    bw.write(qy+","+address+","+longitude+","+latitude);
                                    bw.write("\n");
                                    bw.close();


                                    checklog.add(qy+","+address+","+longitude+","+latitude);
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
//                System.out.println("------------开始保存数据");
//
//                for(Map.Entry<String, String> vo : wrmap.entrySet()){
//                    vo.getKey();
//                    vo.getValue();
//                //    System.out.println(vo.getKey()+" "+vo.getValue());
//                    writefile.writersj(vo.getKey(),vo.getValue());
//                }
                System.out.println("------------开始保存日志");
                for (String s:checklog)
                {
                    writefile.writerrz(s);

                }
//                System.out.println("------------开始保存令牌");
//
//                    writefile.writertoke(pizza73686f7070696e67);
            }



    }

    //设置城市为苏州，拿到收获地址的坐标
    public static List<Map> getzuobiao(String url,String _longitude,String _latitude,Map map) throws Exception {

//        int ipindex=(int)(0+Math.random()*(lip.size()-1));
       // Thread.sleep((long) (1000+Math.random()*(6000)));
       // System.out.println(url);
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
           // System.out.println("换地址网页内容:"+result);
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
            List<Map> maps = FastJsonUtils.toList(result, Map.class);

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
        Thread.sleep((long) (1000+Math.random()*(6000)));
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
            httpGet.setHeader("cookie",cookie2);
            httpGet.setHeader("referer","https://h5.ele.me/search/");
//            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard", "loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("输入店名网页内容:"+result);
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
            Map maps = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);
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
        Thread.sleep((long) (1000+Math.random()*(6000)));
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
            httpGet.setHeader("x-shard", "shopid="+id+"loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("拿店名网页内容:"+result);
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
            //System.out.println("网页内容:"+result);
            Map maps = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);

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
        Thread.sleep((long) (1000+Math.random()*(6000)));
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
            }

            System.out.println(pizza73686f7070696e67);
            System.out.println(cookie2);
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
        String pathname = "E:\\测试都可日志\\日志"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
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



}
