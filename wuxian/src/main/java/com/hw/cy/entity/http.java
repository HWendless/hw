package com.hw.cy.entity;

import com.hw.cy.elesj;
import com.hw.cy.elesjb;
import com.mao.spider.FastJsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URISyntaxException;
import java.util.Map;

public class http {
    //https://h5.ele.me/shop/certification/#/?restaurant_id=E2930788944214393347
    public static void main(String[] args) throws Exception {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;


        client = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder("https://h5.ele.me/shop/certification/#/?restaurant_id=E2930788944214393347");
        HttpGet httpGet = new HttpGet(uriBuilder.build());

        response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println("网页内容:"+result);
        //   System.out.println(url);
      //  Map map = (Map) FastJsonUtils.convertJsonToObject(result, Map.class);
        //     System.out.println(map);

    }
}
