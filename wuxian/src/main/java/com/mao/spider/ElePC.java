package com.mao.spider;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ElePC {

    public static void main(String[] args) throws IOException {

        ReadExcel readExcel = new ReadExcel();
        InputStream is = new FileInputStream("C:\\Users\\yraka\\Desktop\\coco\\七分甜\\苏州.xlsx");
        List<List<String>> excelInfo = readExcel.getExcelInfo(is, "苏州.xlsx");
        for(List<String> strings : excelInfo){
            for(String string : strings){

            }
        }

        List<Map> rest = sendUrl("https://www.ele.me/restapi/bgs/poi/search_poi_nearby?geohash=wttdpcwn012n"+"&keyword="+"常熟万达"+"&latitude=31.29834&limit=20&longitude=120.583191&type=nearby");

        BigDecimal latitude = (BigDecimal) rest.get(0).get("latitude");
        BigDecimal longitude = (BigDecimal)rest.get(0).get("longitude");

        Map foodDatas = sendUrlMap("https://www.ele.me/restapi/shopping/restaurants/search?extras%5B%5D=activity&keyword=7分甜&latitude="+latitude+"&limit=100&longitude="+longitude+"&offset=0&terminal=web");
        List<Map<String, Object>> restaurant_with_foods = (List<Map<String, Object>>) foodDatas.get("restaurant_with_foods");
        for(Map<String, Object>restaurant_with_food : restaurant_with_foods){
            System.out.println(restaurant_with_food);
        }
    }




    public static List<Map> sendUrl(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority", "www.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path", "/restapi/bgs/poi/search_poi_nearby?geohash=wttdpcwn012n&keyword=d&latitude=31.29834&limit=20&longitude=120.583191&type=nearby");
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie: ubt_ssid=dkhx932mn1zbdatylm3k1fbxr3445c5k_2019-07-09", "_utrace=190164111b3ca1b1db2269e6b8168e72_2019-07-09; ut_ubt_ssid=wudotui7v29l1icqbi77iyy56o6i7en2_2019-07-09; cna=U9mNFZYkCiQCAbfRbF8Cx1F8; track_id=1562643997|31c5af8e606267e956c0d9c3e4bed758f6f6f6b71985ad23d1|1aefbd8b32dc70a7ed696fd948826d07; USERID=136129407; UTUSER=136129407; SID=9mvBo8VJEruIPKpXvJJcr226kTTxahTq9Pqg; ZDS=1.0|1562643997|ZUYvGae+rxyD0HFe9ptYlP9Vsnoqt/u4wtaODFAosFtbopaY+UNSWURsCtEgXPrb; tzyy=f206005981683f074299152d85123341; isg=BEZGLRKoZ3WxdTORbSeWdM5GlzxkuY3ZqsC2jzBvH2lEM-ZNmDRzcSzCDy9am4J5; pizza73686f7070696e67=CPuz42fVoxnRVcVQ1x33fbTsKeCbvg_FV4YzWx_TbJgbwOF4BR7gpWFHGwiGdsEj");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("x-shard: loc","120.587876,31.313376");
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

    public static Map sendUrlMap(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority", "www.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path", "/restapi/bgs/poi/search_poi_nearby?geohash=wttdpcwn012n&keyword=d&latitude=31.29834&limit=20&longitude=120.583191&type=nearby");
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie: ubt_ssid=dkhx932mn1zbdatylm3k1fbxr3445c5k_2019-07-09", "_utrace=190164111b3ca1b1db2269e6b8168e72_2019-07-09; ut_ubt_ssid=wudotui7v29l1icqbi77iyy56o6i7en2_2019-07-09; cna=U9mNFZYkCiQCAbfRbF8Cx1F8; track_id=1562643997|31c5af8e606267e956c0d9c3e4bed758f6f6f6b71985ad23d1|1aefbd8b32dc70a7ed696fd948826d07; USERID=136129407; UTUSER=136129407; SID=9mvBo8VJEruIPKpXvJJcr226kTTxahTq9Pqg; ZDS=1.0|1562643997|ZUYvGae+rxyD0HFe9ptYlP9Vsnoqt/u4wtaODFAosFtbopaY+UNSWURsCtEgXPrb; tzyy=f206005981683f074299152d85123341; isg=BEZGLRKoZ3WxdTORbSeWdM5GlzxkuY3ZqsC2jzBvH2lEM-ZNmDRzcSzCDy9am4J5; pizza73686f7070696e67=CPuz42fVoxnRVcVQ1x33fbTsKeCbvg_FV4YzWx_TbJgbwOF4BR7gpWFHGwiGdsEj");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("x-shard: loc","120.587876,31.313376");
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


