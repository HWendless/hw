package com.hw.cy.pojo;

import com.hw.cy.elesj;
import com.hw.cy.elesjb;
import com.hw.cy.entity.http;
import com.mao.spider.FastJsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class pl {
    public static void main1(String[] args) throws  Exception{

        String id="E15998590335007019547";


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

                URIBuilder uriBuilder = new URIBuilder("https://www.ele.me/restapi/ugc/v1/restaurant/"+id+"/ratings?limit=1000&offset=0&record_type=1");
                HttpGet httpGet = new HttpGet(uriBuilder.build());
                httpGet.setHeader("accept", "application/json, text/plain, */*");

                httpGet.setHeader(":scheme", "https");
                httpGet.setHeader(":method","GET");
                httpGet.setHeader("accept-encoding", "gzip, deflate, br");
                httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
                httpGet.setHeader("cookie", "track_id=1565164763|8ec359e16a4206499a45457329587857c1610fcc9abcfa9f14|998dc32b0fdd8b912acc21e9e7cf2332; USERID=235345536; isg=BIWF_HzldE53fFAQHJOSs1BUjcG_QjnUROfwY4fqQLzLHqWQT5ZApjS8LIJNRVGM; SID=v8HLpzWg22C09F0KFrKtvBzgHVvYQ12HXE1A; UTUSER=235345536; ubt_ssid=owwjvh3hcqao7vjwqqk75bv1tz3vwiwz_2019-08-06; ut_ubt_ssid=zbos069qc37o02vx2pj2etcpi6v1wzfb_2019-08-06; cna=mA9PFenUnX4CAdoElg7A9k+W; l=cBN7_AzlqRBVPrukoOCanurza77OSdAYYuPzaNbMi_5Iz_TsWxQOk5RUEE96VsWd_bYB4NSiTkp9-etlmG1fJQTDKdCR.; _utrace=5e6d766822fdd3442cce4a006584a97d_2019-08-06; ZDS=1.0|1565164763|9tuPELNPb+c+GvKQOTRS5FtSAMj+a/CXvXojDVqceDaithPDa5/hmokj3yIQQ1So; pizza7567632f76312f72=5eFWeKtCzB8Xq1BRbIlWwtyoVsYUijOgSiPWB7NHC5NJu0JeCLEbPDpzDKlpQPsO");
                httpGet.setHeader("Cache-Control","no-cache");
                httpGet.setHeader("Connection","Keep-Alive");
                httpGet.setHeader("Host","www.ele.me");
                httpGet.setHeader("Referer","https://www.ele.me/shop/"+id+"/rate");
                httpGet.setHeader("user-agent", elesj.getuseragent());
                httpGet.setHeader("x-shard","shopid="+id+";loc=120.581816,31.315815");
                response = client.execute(httpGet);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "UTF-8");
                System.out.println("网页内容:"+result);
//                Map map = (Map) FastJsonUtils.convertJsonToObject(result,Map.class);
  //              System.out.println(map);

            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                response.close();
                client.close();
            }
        }

    public static void main(String[] args) throws  Exception{

        String id="E15998590335007019547";


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
            //        :authority: h5.ele.me
//:method: GET
//        :path: /pizza/ugc/restaurants/E3910800199512503629/batch_comments?has_content=true&offset=0&limit=20
//                :scheme: https
//        accept: application/json, text/plain, */*
//accept-encoding: gzip, deflate, br
//accept-language: zh-CN,zh;q=0.9
//cookie: ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; USERID=1121690450; UTUSER=1121690450; SID=auwoIjm8gl2vza6ilEqXG9AICZbaUdcJm9Zg; ZDS=1.0|1565162712|zOCEjmUZim7JOWyvj5mr3tKdThiXSRucGyrkrEu318SdB3nolQf+vtx5Cqc+5NIl; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1Ow_hmF0Ci52DprvsRWQ8C1yzpNKCftqDjRNoBBRYcs0; l=cBjITp7RqR6oYHH9BOCwourza77OSIRAguPzaNbMi_5Qm68_6fQOk5-9UFJ6VjWdt3LB4k6UXwe9-etkZkFaOLJTjSbc.; pizza7567632f76332f72=5xMqRQuOWng_3Lh8jjs5aK7DaTxTmh8YL5cyFWw8HFUACzRN9tx2ylKr7DnBJh_H; isg=BLCw6qJ5mfnEfEXwsjYcZYN5gX4C-ZRDeYfXjaoBfYveZVEPUgqS0CgTuSugdUwb
//referer: https://h5.ele.me/shop/
//user-agent: Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Mobile Safari/537.36
//x-shard: shopid=E3910800199512503629;loc=120.527991,31.275678
//x-uab: 118#ZVWZzmuSsAVIaZ/z9ZvjZYquZYT4zHWzagC2Voq4m+JtbFSTyHRVPgZuusqhzeWZZZe4Vfq4zHZxuZZhVHSYViJiusqhzTZZZgZC0GqZZg2ZZZChXHWVZgZuZsiTzeWzZgCZXfTGiZ2ZZZChVbvae2QZZZqCs8dZIASSSqR4zMxVgboivzFZW72gsmbFtK3N7J1HyyryKs8SPw4GasjN7gQZuYPAPQqZZyfsZHoBFUWZXGtCrAalEXKQh96XHlX/aKfo5lhurG8ruSAFLIGLu8YkZnRwCqHqOuRxY4YoZ2TF7bTH+BhqaA2clvY5TW1Bbgwyr0GR0LU5yjRdXE+n0es3w1692SC47j2IlXcznW/ohK8r5o3yVgtUuwHTBYpEDaE70lFuL5ViwhcF1De0apYi23Pn5y82g7MPzVzlJ/9RzWVh4I+xFUyUtDG24oUpigPQDmDLHuz7Pt0X43J93puhXalB8OPG8HGob6c71LW1NROLiFFy9JTqAQ+PBfX4tOeqoaFzRBJN/l9T97BNfObEzF4WF9qBKFRERJIT1mcvipOj5veCZClLL72/E9G0ZBvcIelF/YJPY7WmtUbISFDvkfiAMwHcxC/jZgHk1lvND5rgjAmUoqZmXeCq0BGfogANo42Ni8rGCd3q58cj+jYcRJQTEPDRQYe3oa1azW7ezUKixC8fqqpxMj4HkPWucY0kpqRBkl6ADMZDyBjoXApksml5eI7glk0e+IpgYkm6dF0t352A6CCeQdRQJpXCZGLNU86M8HK+ZHx7ov/stAyOoDZyQbJR2sfzozSLDXi+VTtlfHzZt/G90BGe+FgX78gzIG3aa8jh6/L9EjsE+SwapxzN4DBqY61SdAF=


            URIBuilder uriBuilder = new URIBuilder("https://www.ele.me/restapi/ugc/v1/restaurant/"+id+"/ratings?limit=1000&offset=0&record_type=1");
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("authority","h5.ele.me");
            httpGet.setHeader("method","GET");
            httpGet.setHeader("path", "/pizza/ugc/restaurants/E3910800199512503629/batch_comments?has_content=true&offset=0&limit=20");
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader(":accept","application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", "ubt_ssid=20m2knloy5c80cs9k2dqtcijap3kcj2q_2019-08-05; perf_ssid=l8c383vkxlsx13py0w8itiu1jw39eqq1_2019-08-05; ut_ubt_ssid=t1fwsh9gc4s81vqh3zruv0zxd8rbwowt_2019-08-05; cna=q6jJFTKSDCYCAdoElg4MiyyU; _bl_uid=jOj5ky0Ly6I42Rvs023di51tv1C8; _utrace=50362108d3981130f9514335cccc0527_2019-08-05; track_id=1564993805|53b99560dda2348a4aa7157f94a945a1f767b95cf9b56b5c00|3e56de769b61ef7e7f9b42ed84f7173b; tzyy=010bf680a7183b114a800879e17142d4; perf_ssid=sj3lzwtc3iqcm63lx55ux9abd1btrlt3_2019-08-06; USERID=1121690450; UTUSER=1121690450; SID=auwoIjm8gl2vza6ilEqXG9AICZbaUdcJm9Zg; ZDS=1.0|1565162712|zOCEjmUZim7JOWyvj5mr3tKdThiXSRucGyrkrEu318SdB3nolQf+vtx5Cqc+5NIl; pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1Ow_hmF0Ci52DprvsRWQ8C1yzpNKCftqDjRNoBBRYcs0; l=cBjITp7RqR6oYHH9BOCwourza77OSIRAguPzaNbMi_5Qm68_6fQOk5-9UFJ6VjWdt3LB4k6UXwe9-etkZkFaOLJTjSbc.; pizza7567632f76332f72=5xMqRQuOWng_3Lh8jjs5aK7DaTxTmh8YL5cyFWw8HFUACzRN9tx2ylKr7DnBJh_H; isg=BLCw6qJ5mfnEfEXwsjYcZYN5gX4C-ZRDeYfXjaoBfYveZVEPUgqS0CgTuSugdUwb");
            httpGet.setHeader("referer","https://h5.ele.me/shop/");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard","shopid="+id+";loc=120.581816,31.315815");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("网页内容:"+result);
//                Map map = (Map) FastJsonUtils.convertJsonToObject(result,Map.class);
            //              System.out.println(map);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            response.close();
            client.close();
        }
    }

    }

