package com.hw.cy;

import com.mao.spider.FastJsonUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class cookcs {
    public static void main(String[] args) throws  Exception {
        String pizza73686f7070696e67="_HHDoSEnvf0M2q6qepXm1Jt9Vo5k8BhozFYkjpH2Kv0Qinhl4y70rjwJYorrfc03";
        String mm[]=m().split("=");
      //  String pizza73686f7070696e67=
        if("pizza73686f7070696e67".equals(mm[0]))
        {
            pizza73686f7070696e67=mm[1];
        }

        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {





            String url="https://h5.ele.me/restapi/shopping/v2/restaurants?latitude=31.314892&longitude=120.590211&offset=0&limit=8&extras[]=activities&extras[]=tags&extra_filters=home&rank_id=&terminal=h5";
            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //httpGet.addHeader("x-forwarded-for","39.104.87.57");
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie","ut_ubt_ssid=krub76eo3mc96cdiuze3o0oikjj0np1w_2019-08-01;" +
                    " perf_ssid=igztlsyt6fr4scdj32b96yrehvq206d5_2019-08-01; ubt_ssid=0e6t0sdsjhbbxar3g75nevxn13ghvjb8_2019-08-01;" +
                    " cna=2lTDFQeITCMCAbfQ1zpa+vBt; _utrace=0ac2dfdb2c05cb7373d2dcee5b6d0f2b_2019-08-01;" +
                    " track_id=1564611003|64d20521aa75be97572595e5e8d223ee5132bb2fe215acb08d|486e80947c79f05bfa29d58f29e7ec57;" +
                    " _bl_uid=3qjt9yR6r8dtg316krz1c6Flp1p6; tzyy=010bf680a7183b114a800879e17142d4; USERID=24545713; UTUSER=24545713; " +
                    "SID=wxqs485K7y8fAJvobBak4yMawdYFvabdgKSg; ZDS=1.0|1564613643|9WHQ3iW0QlykuHSIpGPrX+rbzeMwQI6f/TUOtYANUqprGHZPD7oKzaxhONjpPZHtrpHQQ9PQLEXjHOQ9kJbuDA==; " +
                    "l=cBQiJ8bVqUnoOa6BBOfNlurza77OiLAfG1PzaNbMiICPOHfcctYFWZFzRC8kCnGVK6kp53rCLlQLBMokqyCqk4oOPMfU3w1..; " +
                    "pizza73686f7070696e67="+pizza73686f7070696e67+"; isg=BHh4nAJaUZC2X70dc6deRJtKSSYK4dxrLrb3dLLprLMmzRG3QPHx-wasgIVYuJRD");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard", "loc=120.583191,31.29834");
            httpGet.setHeader("x-referer", "//h5.ele.me/");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            Header header[]=response.getAllHeaders();
            System.out.println(header);
            System.out.println(header[5].toString().split(";")[0].split(":")[1]);
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println("网页内容:"+result);
         //   List<Map> maps = FastJsonUtils.toList(result, Map.class);

            //return maps;
        } catch (Exception e) {
            e.printStackTrace();
          //  return null;
        } finally {
            response.close();
            client.close();
        }
    }
    private static String m() throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {





            String url="https://h5.ele.me/restapi/shopping/v3/restaurants?latitude=31.314892&longitude=120.590211&offset=0&limit=8&extras[]=activities&extras[]=tags&extra_filters=home&rank_id=&terminal=h5";
            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //httpGet.addHeader("x-forwarded-for","39.104.87.57");
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie","ut_ubt_ssid=krub76eo3mc96cdiuze3o0oikjj0np1w_2019-08-01;" +
                    " perf_ssid=igztlsyt6fr4scdj32b96yrehvq206d5_2019-08-01; ubt_ssid=0e6t0sdsjhbbxar3g75nevxn13ghvjb8_2019-08-01;" +
                    " cna=2lTDFQeITCMCAbfQ1zpa+vBt; _utrace=0ac2dfdb2c05cb7373d2dcee5b6d0f2b_2019-08-01;" +
                    " track_id=1564611003|64d20521aa75be97572595e5e8d223ee5132bb2fe215acb08d|486e80947c79f05bfa29d58f29e7ec57;" +
                    " _bl_uid=3qjt9yR6r8dtg316krz1c6Flp1p6; tzyy=010bf680a7183b114a800879e17142d4; USERID=24545713; UTUSER=24545713; " +
                    "SID=wxqs485K7y8fAJvobBak4yMawdYFvabdgKSg; ZDS=1.0|1564613643|9WHQ3iW0QlykuHSIpGPrX+rbzeMwQI6f/TUOtYANUqprGHZPD7oKzaxhONjpPZHtrpHQQ9PQLEXjHOQ9kJbuDA==; " +
                    "l=cBQiJ8bVqUnoOa6BBOfNlurza77OiLAfG1PzaNbMiICPOHfcctYFWZFzRC8kCnGVK6kp53rCLlQLBMokqyCqk4oOPMfU3w1..; " +
                    "pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1Jt9Vo5k8BhozFYkjpH2Kv0Qinhl4y70rjwJYorrfc03; isg=BHh4nAJaUZC2X70dc6deRJtKSSYK4dxrLrb3dLLprLMmzRG3QPHx-wasgIVYuJRD");
            httpGet.setHeader("user-agent", elesj.getuseragent());
            httpGet.setHeader("x-shard", "loc=120.583191,31.29834");
            httpGet.setHeader("x-referer", "//h5.ele.me/");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            Header header[]=response.getAllHeaders();
            System.out.println(header);
            System.out.println(header[5].toString().split(";")[0].split(":")[1]);
      //      Set-Cookie: pizza73686f7070696e67=_HHDoSEnvf0M2q6qepXm1JiZ5JbcoMOSS9xZJIsTfQIcCjJ61bmjx_IEZp1rxpF6; Path=/; Max-Age=3600; HttpOnly
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);


            return header[5].toString().split(";")[0].split(":")[1].toString();
        } catch (Exception e) {
            e.printStackTrace();
             return null;
        } finally {
            response.close();
            client.close();
        }

    }
}
