package com.mao.spider;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MeiTuan {

    private static final String COMMON_URL = "https://h5.waimai.meituan.com/waimai/mindex/home";
    public static void main(String[] args) throws IOException {
        Document document = sendUrl(COMMON_URL);
        System.out.println(document);
    }




    public static Document sendUrl(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
            httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.9");
            httpGet.setHeader("Cache-Control","max-age=0");
            httpGet.setHeader("Connection","keep-alive");
            httpGet.setHeader("Cookie","_lxsdk_cuid=16bcf3eebafc8-071e614bc4616-3b654406-1fa400-16bcf3eebafc8; mtcdn=K; lsu=; unc=KqM563498158; IJSESSIONID=kc1mkdo6h0p81p38hp4h8pjs2; iuuid=20775734A27B7004B8B8FC060BC42C098ED7F879C23A9783DB3B455B2810E57E; _lxsdk=20775734A27B7004B8B8FC060BC42C098ED7F879C23A9783DB3B455B2810E57E; webp=1; __utma=74597006.1268727008.1562640454.1562640454.1562640454.1; __utmc=74597006; __utmz=74597006.1562640454.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); cityname=%E5%B9%BF%E5%B7%9E; a2h=1; ndr=i.meituan.com; download_hide_awp_hnumber_hotel_search_search.html=1; i_extend=C_b1GemptyH__a100001__b1; __utmb=74597006.11.9.1562640489803; wm_order_channel=default; au_trace_key_net=default; openh5_uuid=20775734A27B7004B8B8FC060BC42C098ED7F879C23A9783DB3B455B2810E57E; uuid=20775734A27B7004B8B8FC060BC42C098ED7F879C23A9783DB3B455B2810E57E; showTopHeader=show; _lx_utm=utm_source%3DBaidu%26utm_medium%3Dorganic; _hc.v=99d162b6-d3e5-5def-d42c-b52a951f8344.1562640698; ci=80; rvct=80%2C20%2C151%2C1; lat=31.337645; lng=120.418461; webloc_geo=31.275892%2C120.537725%2Cgcj02%2C-1; token=8-xw8t-t6bukYzNQmCQkCIMzjfwAAAAAvQgAAIkRaSpFNs1RqmITBlJGX4z8OmukFbdXPI6J8vHyt3oEAk7pfwA5OTgYnVDLVWEspQ; mt_c_token=8-xw8t-t6bukYzNQmCQkCIMzjfwAAAAAvQgAAIkRaSpFNs1RqmITBlJGX4z8OmukFbdXPI6J8vHyt3oEAk7pfwA5OTgYnVDLVWEspQ; oops=8-xw8t-t6bukYzNQmCQkCIMzjfwAAAAAvQgAAIkRaSpFNs1RqmITBlJGX4z8OmukFbdXPI6J8vHyt3oEAk7pfwA5OTgYnVDLVWEspQ; userId=588710779; userId=588710779; userName=KqM563498158; cssVersion=b2a29baa; userFace=; _lxsdk_s=16bd499e80b-4d7-242-103%7C%7C106");
            httpGet.setHeader("Host","h5.waimai.meituan.com");
            httpGet.setHeader("Upgrade-Insecure-Requests", "1");
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Mobile Safari/537.36");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);
            Document document = Jsoup.parse(result);
            return document;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
}
