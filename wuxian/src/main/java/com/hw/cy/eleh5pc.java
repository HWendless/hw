package com.hw.cy;

import com.mao.spider.FastJsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class eleh5pc {
    private static final  String COOKIE="ubt_ssid=i6lhzsb1dbkt4ki212w4ti055a1u3m02_2019-07-30; perf_ssid=xndv955fvsd1fht3m1rre3e8za5oq922_2019-07-30;" +
            " ut_ubt_ssid=53bgca0ifdj6w7mqxidwqmt876pyimm8_2019-07-30; _bl_uid=7mj1jyebp5192LwOwo3Cvb4tq22n; cna=hqPFFW3b91cCAdoElg417FM+; " +
            "_utrace=2d3e211cd3f09fc33c6eda8b7f912162_2019-07-30; track_id=1564465285|9bfabfcec45371b136f3371bfee725b33b16faf55607c7d703|83" +
            "ad2ad1daa2d45666145e6305edffb7; USERID=235345536; tzyy=010bf680a7183b114a800879e17142d4; FAAS_GRAY_ID=1; UTUSER=235345536; SID=wHiN" +
            "xjHkzHC4235SBJmV6ZMwtjdsoJggCvKA; ZDS=1.0|1564478417|fvx4NbJvlA9G/lufnZxGtc+i5sNi7OpHvdOAyz6fb5EOU6oSfK8p6PKzufTd9Maz; l=cBxCPb2Rqb0Jn" +
            "u30BOCwourza77OSIRAguPzaNbMi_5Qq6T1SC7OkSezOF96VjWd9rTB4k6UXwp9-etlwAL5RaHKraNO.; pizza73686f7070696e67=_HHDoSEnvf37KwrT79GyyyraiPxJ_xjdIT" +
            "zqzHby8_7GqpIQ60GgkTQJFBHY4DlN; isg=BKOjn51E-spBELaBeS0RqkzEMudNmDfaYd88ldUDM4KRFMI2XGgJKKgFDqS_tI_S";
   private static  final String X="118#ZVWZzw5rJGvKqZV8JH1IZYquZYT4zHWzagC2NsTIiIJtbFSTyHRxZZgZZzqhzHRzZgCZXfquZg2zZZFhHluhzZ2ZZ0NTzeWzzgeuVfq4zH2ZZZChXHR4ZggZZzqhzHRZZXquVfq4zH2zkDghVdfFZDZ+ZBqhzpPYZgYGIzQZWC2gsm54EyAj1vexj6DJlSIwyXYbuVlPugZCmrDtKHZzhmWuug0yRgZTtW+v+jgn5grodu+nlD0IXar00tz5a20ucstVMpPvu7dKYI3CoET8dPlOWLTddRQehEFCrF0vUWUbK60U2D8rereHoeshra2rZ2GSAzyyzlZxYNRiPeqBdc14JM6RBmjSacCB9nmJmDfu1d7+wCUw6Na+t3EZwibkwVyPRdnAPoyxiq3aOQ8sG+/hmKMU+FW2DLT+5rfmcIwOKtbQgs9zLx8k3IuoH55K6LxDazc84p9bAMx9c0Bl5WuZpaQvgQAJv4WnT5Y984699Wmu3PX9X1mKN2dLc2oWIwFZcHgvYpQL9d9AlNLmrrVBxzZ2IpqYDgS93BNVt0L1Tze7owtAaScm6z0wCwEW8/yFn7EL36qThTf2CV2J/9HVgAfJN2lK7gK2fEz8JV3w+BbDbvB6q2bzDWhxrChrf+9cpk0kuKss7SQF6PfgnQRttfgASrhc";
    public static void main(String[] args) throws IOException {

     //  String ss= "https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword=\"+\"吴中万达\"+\"&offset=0&limit=20&latitude=31.684107&longitude=120.732811".substring(17);
        //  System.out.println(ss);
        List<Map> rest = sendUrl("https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword="+"吴中万达"+"&offset=0&limit=20&latitude=31.684107&longitude=120.732811");

        BigDecimal latitude = (BigDecimal) rest.get(0).get("latitude");
        BigDecimal longitude = (BigDecimal)rest.get(0).get("longitude");
        Integer cityId = (Integer)rest.get(0).get("city_id");
      //  "\"https://h5.ele.me/restapi/shopping/v2/restaurants/search?offset=0&limit=15&keyword=%E4%B8%83%E5%88%86%E7%94%9C&latitude=31.275278&longitude=120.528172&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5\""
        Map map = findsj("https://h5.ele.me/restapi/shopping/v2/restaurants/search?offset=0&limit=15" +
                "&keyword=%E4%B8%83%E5%88%86%E7%94%9C&latitude="+latitude +
                "&longitude="+longitude+"&search_item_type=3&is_rewrite=1" +
                "&extras[]=activities&extras[]=coupon&terminal=h5");
        System.out.println((Map<String,Object>)map.get("inside"));
        Map m=(Map<Integer,Object>)map.get("inside");
        Map mm=(Map<String,Object>)map.get(0);
        System.out.println("1"+m.get("rank_id"));

        String dp="https://h5.ele.me/restapi/shopping/v2/restaurants/search?offset=0&limit=15&keyword=%E4%B8%83%E5%88%86%E7%94%9C" +
                "&latitude="+latitude+"&longitude="+longitude+"&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5";

        Map map2 = findsj2(dp);
//        List<Map<String,Object>> restaurants = (List<Map<String,Object>>)map.get("inside"); //获取到店家的id和坐标
//        for(Map<String, Object> restaurant : restaurants){
//            String name = (String)restaurant.get("name");
//            if(name.equals("7分甜(吴中万达店)")){
//                String id = (String)restaurant.get("id");
//                System.out.println(id);
//                Map map1 = sendUrlMap("https://h5.ele.me/pizza/shopping/restaurants/"+id+"/batch_shop?user_id=136129407&code=0.5843387289405291&extras=%5B%22activities%22%2C%22albums%22%2C%22license%22%2C%22identification%22%2C%22qualification%22%5D&terminal=h5&latitude="+latitude+"&longitude="+longitude,id);
//                FileWriter fw = new FileWriter("C:\\"+name, true);
//                BufferedWriter bw = new BufferedWriter(fw);
//                String s = FastJsonUtils.convertObjectToJSON(map1);
//                bw.write(s);
//                bw.close();
//                fw.close();
//                System.out.println(map1);
//            }
//        }



    }
//获取输入地址的坐标
    public static List<Map> sendUrl(String rul) throws IOException {
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
            httpGet.setHeader("cookie", "ubt_ssid=i6lhzsb1dbkt4ki212w4ti055a1u3m02_2019-07-30; perf_ssid=xndv955fvsd1fht3m1rre3e8za5oq922_2019-07-30; ut_ubt_ssid=53bgca0ifdj6w7mqxidwqmt876pyimm8_2019-07-30; _bl_uid=7mj1jyebp5192LwOwo3Cvb4tq22n; cna=hqPFFW3b91cCAdoElg417FM+; _utrace=2d3e211cd3f09fc33c6eda8b7f912162_2019-07-30; track_id=1564465285|9bfabfcec45371b136f3371bfee725b33b16faf55607c7d703|83ad2ad1daa2d45666145e6305edffb7; USERID=235345536; UTUSER=235345536; SID=I3mmey5E8ntkhnMsi6yKfMffB6edS8eaXEXw; ZDS=1.0|1564465285|hlBKspAleD18r5AMOUnhu6kEM3jNGJnXD+cg1+zInJp7ioEaE51cQgU3YbsdMt6o; tzyy=010bf680a7183b114a800879e17142d4; pizza73686f7070696e67=_HHDoSEnvf37KwrT79Gyy619sBkxtbg6wklR9gaikILMPUbyPJGuMW-e916Fuo-N; isg=BFZW-VWvFx7EECO6TDKMsSFzpwxY95ox_MiJDsC-rjnlg_UdK4WfQuA1H1_KK5JJ");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36");
            httpGet.setHeader("x-shard", "loc=120.732811,31.684107");
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
            httpGet.setHeader("cookie", "ubt_ssid=i6lhzsb1dbkt4ki212w4ti055a1u3m02_2019-07-30; perf_ssid=xndv955fvsd1fht3m1rre3e8za5oq922_2019-07-30; ut_ubt_ssid=53bgca0ifdj6w7mqxidwqmt876pyimm8_2019-07-30; _bl_uid=7mj1jyebp5192LwOwo3Cvb4tq22n; cna=hqPFFW3b91cCAdoElg417FM+; _utrace=2d3e211cd3f09fc33c6eda8b7f912162_2019-07-30; track_id=1564465285|9bfabfcec45371b136f3371bfee725b33b16faf55607c7d703|83ad2ad1daa2d45666145e6305edffb7; USERID=235345536; UTUSER=235345536; SID=I3mmey5E8ntkhnMsi6yKfMffB6edS8eaXEXw; ZDS=1.0|1564465285|hlBKspAleD18r5AMOUnhu6kEM3jNGJnXD+cg1+zInJp7ioEaE51cQgU3YbsdMt6o; tzyy=010bf680a7183b114a800879e17142d4; pizza73686f7070696e67=_HHDoSEnvf37KwrT79Gyy619sBkxtbg6wklR9gaikILMPUbyPJGuMW-e916Fuo-N; isg=BFZW-VWvFx7EECO6TDKMsSFzpwxY95ox_MiJDsC-rjnlg_UdK4WfQuA1H1_KK5JJ");
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

    public static Map findsj(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",rul.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", eleh5pc.COOKIE);
            httpGet.setHeader("upgrade-insecure-requests", "1");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Mobile Safari/537.36");
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

    public static Map findsj2(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",rul.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", eleh5pc.COOKIE);
            httpGet.setHeader("upgrade-insecure-requests", "1");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Mobile Safari/537.36");
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
            httpGet.setHeader("x-uab", eleh5pc.X);
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);
            Map map = (Map) FastJsonUtils.convertJsonToObject(result,Map.class);

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
}
