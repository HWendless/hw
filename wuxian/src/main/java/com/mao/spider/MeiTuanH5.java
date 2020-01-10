package com.mao.spider;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class MeiTuanH5 {

    public static String wm_actual_latitude = "31275818";
    public static String wm_actual_longitude = "120537649";
    public static String actualLat = "31.839181";
    public static String actualLng = "120.537649";


    public static void main(String[] args) throws IOException {

        String shopName = "7分甜（吴中万达店）";
        Map rest = sendUrlMap("https://maf.meituan.com/search?key=be9427ec-bca4-4bfa-b981-9314f6a1adc7" +
                                    "&location=120.537649%2C31.275818&region=NEARBY&orderby=weight&radius=50000&pageSize=20&page=1&" +
                                    "city="+"苏州市"+"&keyword="+"吴中万达"+"&wm_latitude=0&wm_longitude=0&wm_actual_latitude=31275818&wm_actual_longitude=120537649&_=1562744515267&" +
                                    "X-FOR-WITH=4g31bVKB23wEHurqFYPFbU60rtzuBk%2BQWAcIJzSeXTiiBXNyGYqZRC8EECWDlMEo77MAQYHwOICWnUQqKEUAo%2B%2BGISA4GlYjSydpjaCuKIol3cro6Cf7seAxS4XBPokI0grTK7ZFa1o7Dp4HPVdGtA%3D%3D");
        System.out.println(rest);
        Map<String,Object> result = (Map<String,Object>)rest.get("result");
        List<Map<String,Object>> address = (List<Map<String,Object>>)result.get("pois");
        String location = (String)address.get(0).get("location");
        String[] locs = location.split(",");
        String latitude = locs[1];
        String longitude = locs[0];
        Map<String,String> params = new HashMap<String, String>();
        params.put("keyword","7分甜");
        params.put("initialLat",latitude);
        params.put("initialLng",longitude);
        params.put("actualLat",actualLat);
        params.put("actualLng",actualLng);
        params.put("geotype","2");
        params.put("categorytype","0");
        params.put("suggestGlobalId","746287682147024737");
        params.put("wm_latitude",latitude.replace(".",""));
        params.put("wm_longitude",longitude.replace(".",""));
        params.put("wm_actual_latitude",wm_actual_latitude);
        params.put("wm_actual_longitude",wm_actual_longitude);

        Map map = postUrlMap("https://i.waimai.meituan.com/openh5/search/suggestv8?_=1562751", params);
        System.out.println(map);

        Map<String, Object> data =  (Map<String, Object>)map.get("data");
        List<Map<String,Object>> suggests = (List<Map<String,Object>>)data.get("suggests");
        for(Map<String,Object> suggest : suggests){
            String content = (String)suggest.get("content");
            String cont = convString(content);
            if(isSame(convString(shopName),cont)){
                Map<String,Object> poiAdditionInfo = (Map<String,Object>)suggest.get("poiAdditionInfo");
                String wmPoiId = (String)poiAdditionInfo.get("wmPoiId");
                System.out.println(wmPoiId);

                Map<String,String> foodParam = new HashMap<String, String>();
                foodParam.put("geoType","2");
                foodParam.put("mtWmPoiId",wmPoiId);
                foodParam.put("dpShopId","-1");
                foodParam.put("source","");
                foodParam.put("skuId","");
                foodParam.put("uuid","20775734A27B7004B8B8FC060BC42C098ED7F879C23A9783DB3B455B2810E57E");
                foodParam.put("platform","3");
                foodParam.put("partner","4");
                foodParam.put("originUrl","https://h5.waimai.meituan.com/waimai/mindex/menu?mtShopId=1049953185509935&initialLat="+latitude+"&initialLng="+longitude+"&actualLat="+actualLat+"&actualLng="+actualLng);
                foodParam.put("riskLevel","71");
                foodParam.put("optimusCode","10");
                foodParam.put("wm_latitude",latitude.replace(".",""));
                foodParam.put("wm_longitude",longitude.replace(".",""));
                foodParam.put("wm_actual_latitude",wm_actual_latitude);
                foodParam.put("wm_actual_longitude",wm_actual_longitude);
                foodParam.put("_token","");
                Map food = postUrlMap("https://i.waimai.meituan.com/openh5/poi/food?_=1562754074055&X-FOR-WITH=0P9p2oN3oZ%2FuR4l3H3gXixUTrw5hda01ISSuGE1%2B%2FVKnoeKiKbR2xwFyJByQRyg3hotvtd2WKTE1rNLqUJmO38Qni%2BkTKDjDnvSQQN75rgQMXhiST5%2FnUQzy03fC%2FnyxaQsFEY5p7koBX2LLHTDN2Q%3D%3D",foodParam);

                System.out.println(food);
            }

        }



    }


    public static List<Map> sendUrl(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Accept", "application/json");
            httpGet.setHeader("Origin","https://h5.waimai.meituan.com");
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
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

    public static Map postUrlMap(String url, Map<String,String> params) throws IOException {


        //HttpClients.createDefault()等价于 HttpClientBuilder.create().build();
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);

        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-Type","application/x-www-form-urlencoded");
        httpost.setHeader("Origin","https://h5.waimai.meituan.com");
        httpost.setHeader("Referer","https://h5.waimai.meituan.com/waimai/mindex/search?viewKeyword=&searchKeyword=&tgtStids=&sceneType=&mode=search");
        httpost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        CloseableHttpResponse  httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpost);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            Map map = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Map postFoodMap(String url, Map<String,String> params) throws IOException {


        //HttpClients.createDefault()等价于 HttpClientBuilder.create().build();
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpost = new HttpPost(url);

        httpost.setHeader("Accept","application/json");
        httpost.setHeader("Content-Type","application/x-www-form-urlencoded");
        httpost.setHeader("Origin",params.get("originUrl"));
        httpost.setHeader("Referer","https://h5.waimai.meituan.com/waimai/mindex/search?viewKeyword=&searchKeyword=&tgtStids=&sceneType=&mode=search");
        httpost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");

        //组织请求参数
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key : keySet) {
                paramList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        try {
            httpost.setEntity(new UrlEncodedFormEntity(paramList, "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        CloseableHttpResponse  httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(httpost);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            Map map = (Map)FastJsonUtils.convertJsonToObject(result,Map.class);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {  //关闭连接、释放资源
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static Map sendUrlMap(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path","/restapi/bgs/poi/search_poi_nearby_alipay?keyword=%E5%B8%B8%E7%86%9F%E4%B8%87%E8%BE%BE&offset=0&limit=20&latitude=31.684107&longitude=120.732811");
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", "ubt_ssid=dkhx932mn1zbdatylm3k1fbxr3445c5k_2019-07-09; _utrace=190164111b3ca1b1db2269e6b8168e72_2019-07-09; ut_ubt_ssid=wudotui7v29l1icqbi77iyy56o6i7en2_2019-07-09; perf_ssid=wqi0n6tgrph198c80tvb3c59uafyjt9w_2019-07-09; cna=U9mNFZYkCiQCAbfRbF8Cx1F8; track_id=1562643997|31c5af8e606267e956c0d9c3e4bed758f6f6f6b71985ad23d1|1aefbd8b32dc70a7ed696fd948826d07; USERID=136129407; UTUSER=136129407; SID=9mvBo8VJEruIPKpXvJJcr226kTTxahTq9Pqg; ZDS=1.0|1562643997|ZUYvGae+rxyD0HFe9ptYlP9Vsnoqt/u4wtaODFAosFtbopaY+UNSWURsCtEgXPrb; tzyy=f206005981683f074299152d85123341; _bl_uid=azjh5x4Lv2sagqaqkzghwpLtdyF2; pizza73686f7070696e67=_HHDoSEnvf1hEfcSRq4_AwDesOo5Ykn4P685YSE8zu64Pk6SAKDsGO3536j8SFAO; isg=BE1NlutNLAggq4gk8noNOXnTXGlrNIZE_XXtmo_QnORThmg4cXhTzRpQ9FpFRpm0");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("x-shard", "loc=120.732811,31.684107");
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

    public static Map sendUrlMap(String rul, String id) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path","/restapi/bgs/poi/search_poi_nearby_alipay?keyword=%E5%B8%B8%E7%86%9F%E4%B8%87%E8%BE%BE&offset=0&limit=20&latitude=31.684107&longitude=120.732811");
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", "ubt_ssid=dkhx932mn1zbdatylm3k1fbxr3445c5k_2019-07-09; _utrace=190164111b3ca1b1db2269e6b8168e72_2019-07-09; ut_ubt_ssid=wudotui7v29l1icqbi77iyy56o6i7en2_2019-07-09; perf_ssid=wqi0n6tgrph198c80tvb3c59uafyjt9w_2019-07-09; cna=U9mNFZYkCiQCAbfRbF8Cx1F8; track_id=1562643997|31c5af8e606267e956c0d9c3e4bed758f6f6f6b71985ad23d1|1aefbd8b32dc70a7ed696fd948826d07; USERID=136129407; UTUSER=136129407; SID=9mvBo8VJEruIPKpXvJJcr226kTTxahTq9Pqg; ZDS=1.0|1562643997|ZUYvGae+rxyD0HFe9ptYlP9Vsnoqt/u4wtaODFAosFtbopaY+UNSWURsCtEgXPrb; tzyy=f206005981683f074299152d85123341; _bl_uid=azjh5x4Lv2sagqaqkzghwpLtdyF2; pizza73686f7070696e67=_HHDoSEnvf1hEfcSRq4_AwDesOo5Ykn4P685YSE8zu64Pk6SAKDsGO3536j8SFAO; isg=BE1NlutNLAggq4gk8noNOXnTXGlrNIZE_XXtmo_QnORThmg4cXhTzRpQ9FpFRpm0");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("x-shard", "shopid="+id+"loc=120.732811,31.684107");
            httpGet.setHeader("x-uab", "118#ZVWZz2c2TUMWtZVQRZLIZYquZYT4zHWzagC2NsTIiIJtbFSTyHRxZZgZZzqhzHRzZgCZXfquZg2zZZFhHluhzZ2ZZ0NTzeWzzgeuVfq4zH2ZZZChXHR4ZggZZzqhzHRZZXquVfq4zH2zZDbhVHW42zzo2XZTwevXZzqu6levZ4uD2CK9sbYruNjq+Cj76/cNfHfJ0lAgoBg7ZZe3L4PXZZxqfZCCl0EoZYiP+7Mw7YyEuyVF/w16BBiby0RcE3e9XYal0Zq7Jjl8b1JajqeoB493skZsyXyDB0+lHoFPJln+wlnMfpg2aNkNf+qw2Oan/p1LJWHMcf5A+4o3QIojxPDlGpBUck9sLavlNsDBcWfsn/csI05M95VQdoI1gR0Fpx6GLt4aqO0vhhr7VoufyJdWuOYpR1YGr+ko+ush/cLGuddy2MeVL1MqJ7/QTvSaWjx20VVF1vYHS7p4752ShAVGZ02gl8kZwuvGAyUG0yvj3pdlTzdSIhWwJiBTyUreZ38L5sX1RUDFBLXx33mwVanp7SfuInv1YvmJ58TUBHswSOa9Lx7EWTVcRCV5fD08lkhIBzUhS1SCfn3leB0bWsLvKr+0gARwRbuJGAaFMQLx1bMM6XegXTuG1vOx5ZY0qJ/v3Ir/NoeijnE9eH63Ne==");
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
    public static Document sendUrlDoc(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path","/restapi/bgs/poi/search_poi_nearby_alipay?keyword=%E5%B8%B8%E7%86%9F%E4%B8%87%E8%BE%BE&offset=0&limit=20&latitude=31.684107&longitude=120.732811");
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", "ubt_ssid=dkhx932mn1zbdatylm3k1fbxr3445c5k_2019-07-09; _utrace=190164111b3ca1b1db2269e6b8168e72_2019-07-09; ut_ubt_ssid=wudotui7v29l1icqbi77iyy56o6i7en2_2019-07-09; perf_ssid=wqi0n6tgrph198c80tvb3c59uafyjt9w_2019-07-09; cna=U9mNFZYkCiQCAbfRbF8Cx1F8; track_id=1562643997|31c5af8e606267e956c0d9c3e4bed758f6f6f6b71985ad23d1|1aefbd8b32dc70a7ed696fd948826d07; USERID=136129407; UTUSER=136129407; SID=9mvBo8VJEruIPKpXvJJcr226kTTxahTq9Pqg; ZDS=1.0|1562643997|ZUYvGae+rxyD0HFe9ptYlP9Vsnoqt/u4wtaODFAosFtbopaY+UNSWURsCtEgXPrb; tzyy=f206005981683f074299152d85123341; _bl_uid=azjh5x4Lv2sagqaqkzghwpLtdyF2; pizza73686f7070696e67=_HHDoSEnvf1hEfcSRq4_AwDesOo5Ykn4P685YSE8zu64Pk6SAKDsGO3536j8SFAO; isg=BE1NlutNLAggq4gk8noNOXnTXGlrNIZE_XXtmo_QnORThmg4cXhTzRpQ9FpFRpm0");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("x-shard", "loc=120.732811,31.684107");
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


    public static String convString(String shopName){
        String s1 = shopName.replace("7分甜","");
        String s2 = s1.replace("七分甜","");
        String s3 = s2.replace("（","");
        String s4 = s3.replace("）","");
        String s5 = s4.replace("(","");
        String s6 = s5.replace(")","");
        return s6;
    }

    public static boolean isSame(String shopName, String content){
        char[] chars = shopName.toCharArray();
        int same = 0;
        for(char c : chars){
           if(content.indexOf(c) != -1){
               same++;
           }
        }

        return shopName.length()/2 <same;

    }
}


