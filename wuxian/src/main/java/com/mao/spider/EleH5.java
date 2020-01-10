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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class EleH5 {



    public static void main(String[] args) throws IOException {


        List<Map> rest = sendUrl("https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword="+"吴中万达"+"&offset=0&limit=20&latitude=31.684107&longitude=120.732811");

        BigDecimal latitude = (BigDecimal) rest.get(0).get("latitude");
        BigDecimal longitude = (BigDecimal)rest.get(0).get("longitude");
        Integer cityId = (Integer)rest.get(0).get("city_id");
        Map map = sendUrlMap("https://h5.ele.me/restapi/shopping/v1/typeahead?kw=7分甜&latitude=" + latitude + "&longitude=" + longitude + "&city_id=" + cityId);
        List<Map<String,Object>> restaurants = (List<Map<String,Object>>)map.get("restaurants");
        for(Map<String, Object> restaurant : restaurants){
            String name = (String)restaurant.get("name");
            if(name.equals("7分甜(吴中万达店)")){
                String id = (String)restaurant.get("id");
                System.out.println(id);
                Map map1 = sendUrlMap("https://h5.ele.me/pizza/shopping/restaurants/"+id+"/batch_shop?user_id=136129407&code=0.5843387289405291&extras=%5B%22activities%22%2C%22albums%22%2C%22license%22%2C%22identification%22%2C%22qualification%22%5D&terminal=h5&latitude="+latitude+"&longitude="+longitude,id);
                FileWriter fw = new FileWriter("C:\\"+name, true);
                BufferedWriter bw = new BufferedWriter(fw);
                String s = FastJsonUtils.convertObjectToJSON(map1);
                bw.write(s);
                bw.close();
                fw.close();
                System.out.println(map1);
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
}
