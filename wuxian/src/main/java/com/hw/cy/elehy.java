package com.hw.cy;

import com.hw.cy.entity.URLzh;
import com.hw.cy.entity.writefile;
import com.hw.cy.pojo.geographicalPosition;
import com.mao.spider.FastJsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class elehy {
    private static final  String COOKIE="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; FAAS_GRAY_ID=99; USERID=235345536; UTUSER=235345536; SID=FpXZryd7QR16WYCHpqeZX5OPFHIqeu8poNHA; ZDS=1.0|1565077711|yW3PWUWg3ktAdPrZneSWhjeXIm2fI4MyEOROShzfqYjQWocJvoO1NwoXCydFf/3j; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1CeVJjsOzsJNwGZ-RHb59i-2ODpg0CaMsKkFZzD9KY-Y; isg=BD8_wo_wXj-jblotKfv7ACg8zhPJJJPGCL6DuNEM2u414F5i2fSpFyaxJva7uGs-";
//    private static  final String COOKIE2="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; FAAS_GRAY_ID=99; USERID=235345536; UTUSER=235345536; SID=KMeBYz0f6Y5A6HcjtuvTd086qxgK4Q2d5JtA; ZDS=1.0|1565083241|DYCRY15beu4CkEofx4LTLbuo/htGHuLfhlm5RbXUUVZ87PA+jUk51QDJRAgJcT5i; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1HdoS4-DGNdSdQq-v_O2N1iYYsRXxVpohaf-NuyH0Ob5; isg=BMLCuA8hK9TuhTeG1Iwuux3jE8gkk8at3VGu0wzb7zXtX2PZ9SEcvtTeCx2GDz5F";
    private static final String COOKIE3="ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; FAAS_GRAY_ID=99; USERID=235345536; UTUSER=235345536; SID=QvnaJnjOgYCUY3nlVGQlcjeC90lcxMqpwDgQ; ZDS=1.0|1565137176|U3tynouC6eCCEnMUujAm0j1tg8M6sMV2SsEP7HRVtcFW7FAxBaEMkOU9YG/nA1B+; l=cBjITp7RqR6oYD_-BOfN-urza77OEQOf1sPzaNbMiICP9O1pVWllWZFZjNL9CnGVLsKk-3rCLlQLBWLsRy4EQ2hdKVXnveVV.; isg=BAcHaNaTFtRB35JVIeMjWKBUlrvRDNvuJRMY-dn0Hha9SCcK4d5_PkRJ6kizoLNm";
    private static  final String X="118#ZVWZza9fMVIPmZVQJzkDghVdfFZDZ+ZBqhzpPYZgYGIzFZW72gsmGfmBIkcXOSA45DoXuAkCFmMIHasgQZuYPAPQqZZy7R9eFEJfTw4WXlCHedGCR/63No7DTAeIqZnK7WUh3c0+3LKx/aUCHPEc6gli6w95iUYiCRQgucwA9yUuH1VNMPt+OhSz+LxhGZh0xzKq9lo4zOkcWbT4tpZ40Cw1n23D6IxALALWsX731QC76ysqmhdinUokdTsGufQZAHNgVftgdryNNmvugpPPVhyHK3Q5xWJJ+IZaaQME9tmIh3mCDrv9lOOo6GyEI0QbjYjFrcQjLFnecM7EHS7C3Fub0JKaIWll6mrAXqwhRSv5LeXW3vV6kA3tSL9J3JjGdpSgSexNWxdEGDB5CDoPW7A7ki2BFqq2Z5G6UZ60xsLMjZi97Lk5E7P4prLHiI0p0HpONeuqj0651lXXM/7+uW78oU/K6Hy48zvXEhGLzZWXc510hkACOj/NUqzQY/05WC06iUH==";
        public static void main(String[] args) throws Exception {
            //设置地址
            Set eleidset=new HashSet();
            FileWriter fw = new FileWriter("E:\\一点点\\shj");
            BufferedWriter bw = new BufferedWriter(fw);
          //  String qy="江苏省虎丘区狮山街道";
            List<geographicalPosition> list=jxzb.getcs();
            for(geographicalPosition p:list)
            {
                if("南京市".equals(p.getCityname()))
                {
                    Map<String,String> mapcs=p.getlnglat();
                    String latitude_=mapcs.get("lat");
                    String longitude_=mapcs.get("lng");
                    List<Map> lm=getzuobiao("https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword="+p.getssqj()+"&offset=0&limit=20&latitude="+latitude_+"&longitude="+longitude_);
                    for(int i=0;i<lm.size();i++)
                    {
                        BigDecimal longitude=(BigDecimal)lm.get(i).get("longitude");
                        BigDecimal latitude=(BigDecimal)lm.get(i).get("latitude");
                    //    Map mapdianpu =getdianmingID("https://h5.ele.me/restapi/shopping/v1/restaurants/search?offset=0&limit=30&keyword="+ URLzh.getURLEncoderString("一点点")+"&latitude="+latitude+"&longitude="+longitude+"&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5",longitude,latitude);

                       if( eleidset.add("https://h5.ele.me/restapi/shopping/v1/restaurants/search?offset=0&limit=30&keyword="+ URLzh.getURLEncoderString("一点点")+"&latitude="+latitude+"&longitude="+longitude+"&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5"))
                        {
                            bw.write("https://h5.ele.me/restapi/shopping/v1/restaurants/search?offset=0&limit=30&keyword=" + URLzh.getURLEncoderString("一点点") + "&latitude=" + latitude + "&longitude=" + longitude + "&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5");
                            bw.write("\n");
                        }
                    }
//                    if( eleidset.add("https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword="+p.getssqj()+"&offset=0&limit=20&latitude="+latitude_+"&longitude="+longitude_));
//                        {
//                            bw.write("https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword="+p.getssqj()+"&offset=0&limit=20&latitude="+latitude_+"&longitude="+longitude_);
//                            bw.write("\n");
//                        }




                }
            }
            bw.close();


    }

    //设置城市为苏州，拿到收获地址的坐标
    public static List<Map> getzuobiao(String url) throws IOException {
//        try {
//            int i=(int)(2000+Math.random()*(7000));
//            System.out.println("设置城市停止时间"+i);
//            Thread.currentThread().sleep((i));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("x-forwarded-for","39.10487.57");
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
//      COOKIE      httpGet.setHeader("cookie", "ubt_ssid=i6lhzsb1dbkt4ki212w4ti055a1u3m02_2019-07-30; perf_ssid=xndv955fvsd1fht3m1rre3e8za5oq922_2019-07-30; ut_ubt_ssid=53bgca0ifdj6w7mqxidwqmt876pyimm8_2019-07-30; _bl_uid=7mj1jyebp5192LwOwo3Cvb4tq22n; cna=hqPFFW3b91cCAdoElg417FM+; _utrace=2d3e211cd3f09fc33c6eda8b7f912162_2019-07-30; track_id=1564465285|9bfabfcec45371b136f3371bfee725b33b16faf55607c7d703|83ad2ad1daa2d45666145e6305edffb7; USERID=235345536; UTUSER=235345536; SID=I3mmey5E8ntkhnMsi6yKfMffB6edS8eaXEXw; ZDS=1.0|1564465285|hlBKspAleD18r5AMOUnhu6kEM3jNGJnXD+cg1+zInJp7ioEaE51cQgU3YbsdMt6o; tzyy=010bf680a7183b114a800879e17142d4; pizza73686f7070696e67=_HHDoSEnvf37KwrT79Gyy619sBkxtbg6wklR9gaikILMPUbyPJGuMW-e916Fuo-N; isg=BFZW-VWvFx7EECO6TDKMsSFzpwxY95ox_MiJDsC-rjnlg_UdK4WfQuA1H1_KK5JJ");
      //      httpGet.setHeader("cookie", "ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; USERID=235345536; UTUSER=235345536; SID=da7TTspj4U1bQ5AyBygiT3CO75aY8Q4hoR1Q; ZDS=1.0|1564993805|gq6HO4OKv1CDv/+s+vWn6oHTiLCeS2tZRAof++DGvO+AwBiphpV/pUu0atTbXLvw; tzyy=010bf680a7183b114a800879e17142d4; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1PaovxDdqJphFzOq-RkHTB_-edN5qb0uw4o1cDwFoBf3; isg=BDU14HbMhLPYDOAPz81RQmYSRLHvsunEFqDZGrda8Kz7jlSAfwFXkxUP3BQdzgF8");
           // httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("cookie", elehy.COOKIE3);
                    httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard", "loc=120.619585,31.299379");
            httpGet.setHeader("x-referer", "https://h5.ele.me/search/");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);
            List<Map> maps = FastJsonUtils.toList(result, Map.class);

            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
    //在搜索框输入店名
    public static Map getdianmingID(String url, BigDecimal longitude,BigDecimal latitude) throws IOException {
           // String url1="https://h5.ele.me/restapi/shopping/v2/restaurants/search?offset=0&limit=15&keyword=%E4%B8%80%E7%82%B9%E7%82%B9&latitude=31.291209&longitude=120.569963&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5";

//        try {
//            int i=(int)(2000+Math.random()*(7000));
//            System.out.println("输入店名停止时间"+i);
//            Thread.currentThread().sleep((i));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("x-forwarded-for","39.10487.57");
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", elehy.COOKIE);
            httpGet.setHeader("referer","https://h5.ele.me/search/");
//            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard", "loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);
         //   System.out.println(url);
            Map map = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);
       //     System.out.println(map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
    //拿掉店的id之后，拿店的数据
    public static Map getdianshuju(String url, String id, BigDecimal longitude,BigDecimal latitude) throws IOException {

//        try {
//            int i=(int)(2000+Math.random()*(7000));
//            System.out.println("开始拿数据停止时间"+i);
//            Thread.currentThread().sleep((i));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.addHeader("x-forwarded-for","39.10487.57");
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", elehy.COOKIE3);
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            httpGet.setHeader("x-shard", "shopid="+id+"loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab",elesj.getx_uab());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);
            Map map = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }

}
