package com.hw.cy;

import com.hw.cy.entity.exeutils;
import com.mao.spider.FastJsonUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.Index;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ele {
    private  static  final  String COOKIE="ubt_ssid=rcndnoch8v1q2zhv6rzy7x8voh27zylh_2019-07-31; perf_ssid=z7naqg21bcmfdeulu35jdjddptrep51i_2019-07-31; ut_ubt_ssid=ncehkd12f819liiwoz06sqinjlwji9r0_2019-07-31; _bl_uid=jUj8hye0q6Fi0aztC363fw0g6w70; cna=ztHHFZ1tLT4CAdoElg73r3dZ; _utrace=4f70186644bdb2118833a97cf6eef509_2019-07-31; track_id=1564533743|f203fd3b0a2312cfb581fbece00ded36a63e10f3eaa04d1c28|e7e2b3039989cb3329b738362078fba4; USERID=235345536; tzyy=010bf680a7183b114a800879e17142d4; pizza-rc-ca-result=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhbGciOiJIUzI1NiIsImV4cCI6MTU2NDU2Nzk4MSwic2FsdDEiOiI4ZjFkMjRmMDY1Y2E0YTZjY2I1Y2NiZDY1ZDBkNmM2ZCIsInNhbHQyIjoiYzQ0ZDk2NzNlZTIxNjIzNDQwOGQ2NDgzMTEyZTFhODYifQ.Q6RxU6Ol3h_uVY4e8SD-ZY1VdxCnlFnuli_SS9bOVKI; pizza73686f7070696e67=_HHDoSEnvf37KwrT79Gyy2BE5N6wxfg0nBebcyjDkLr43AKnJ7bwJHTHQCk0eIdo; UTUSER=235345536; SID=cDXaOFgz1SHQfeuCaUnX1BJwJjsF213ZiJdw; ZDS=1.0|1564566327|4rb+OTz/uVV2tb/zPQmUVpj7dk8Qk5WjlbX/50UZU9IcStOxZWlmQ91pqv3Leb10; isg=BOXl1bj71KUwuzAH62fvVL4u9KEfIpm08yU6k-fLHZwk_gVwr3INhHqfiCItfrFs";
    private static  final String X="118#ZVWZza9fMVIPmZVQJH1IZYquZYT4zHWzagC2NsTIiIJtbFSTyHRxZZgZZzqhzHRzZgCZXfquZg2zZZFhHluhzZ2ZZ0NTzeWzzgeuVfq4zH2ZZZChXHR4ZggZZzqhzHRZZXquVfq4zH2zkDghVdfFZDZ+ZBqhzpPYZgYGIzFZW72gsmGfmBIkcXOSA45DoXuAkCFmMIHasgQZuYPAPQqZZyfmZe+7by2ZXGtCtF8Qm9N7R9eFEJfTw4WXlCHedGCR/63No7DTAeIqZnK7WUh3c0+3LKx/aUCHPEc6gli6w95iUYiCRQgucwA9yUuH1VNMPt+OhSz+LxhGZh0xzKq9lo4zOkcWbT4tpZ40Cw1n23D6IxALALWsX731QC76ysqmhdinUokdTsGufQZAHNgVftgdryNNmvugpPPVhyHK3Q5xWJJ+IZaaQME9tmIh3mCDrv9lOOo6GyEI0QbjYjFrcQjLFnecM7EHS7C3Fub0JKaIWll6mrAXqwhRSv5LeXW3vV6kA3tSL9J3JjGdpSgSexNWxdEGDB5CDoPW7A7ki2BFqq2Z5G6UZ60xsLMjZi97Lk5E7P4prLHiI0p0HpONeuqj0651lXXM/7+uW78oU/K6Hy48zvXEhGLzZWXc510hkACOj/NUqzQY/05WC06iUH==";
        public static void main(String[] args) throws IOException {
        exeutils exeutils = new exeutils();
        //名字
        String shopname="";
        String dzshopid="";
        String address="";
        String bijiao="";
        //先读取表格获得店名和地址
        InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\蜜雪冰城地址2.xlsx");
        List<List<String>> exelist=exeutils.getExcelInfo(is,"C:\\Users\\Administrator\\Desktop\\蜜雪冰城地址2.xlsx");
        for(List<String> stringlist : exelist)
        {
            for(int index=0;index<stringlist.size();index++)
            {
                if(index==0)
                {
                    shopname=stringlist.get(index);
                }
                if(index==1)
                {
                    dzshopid=stringlist.get(index);
                }
                if(index==2)
                {
                    address= stringlist.get(index);
                }
            }
            bijiao=shopname.replace("蜜雪冰城(","").replace("店)","");


                    if(!StringUtils.isBlank(address))
                    {
                        address=bijiao;
                    }

            //地址
            //输入地址，返回搜索结果
            List<Map> map=getzuobiao("https://h5.ele.me/restapi/bgs/poi/search_poi_nearby_alipay?keyword="+address+"&offset=0&limit=20&latitude=31.684107&longitude=120.732811");
            if(map.size()>0)
            {
                BigDecimal longitude=(BigDecimal)map.get(0).get("longitude");
                BigDecimal latitude=(BigDecimal)map.get(0).get("latitude");
                Map mapdianpu =getdianmingID("https://h5.ele.me/restapi/shopping/v2/restaurants/search?offset=0&limit=15&keyword="+shopname+"&latitude="+latitude+"&" +
                        "longitude="+longitude+"&search_item_type=3&is_rewrite=1&extras[]=activities&extras[]=coupon&terminal=h5",longitude,latitude);
                Map map2=(Map) mapdianpu.get("inside");  //restaurant_with_foods
                Map map3=(Map) map2.get(0);
                List<Map> map4=(List<Map>)map3.get("restaurant_with_foods");
                //     System.out.println(map4);
                for(Map m:map4)
                {
                    // System.out.println(m.get("restaurant"));
                    Map map5=(Map)m.get("restaurant");
                    System.out.println(map5.get("id"));
                    System.out.println(map5.get("name"));
                    String dianmingstring=map5.get("name").toString();
                    String dianID=map5.get("id").toString();
                    //拿到店名后匹配
                    if(dianmingstring.indexOf("蜜雪冰城")!=-1&&dianmingstring.indexOf(bijiao)!=-1)  //String id, BigDecimal longitude,BigDecimal latitude
                    {
                        Map  sj=getdianshuju("https://h5.ele.me/pizza/shopping/restaurants/"+dianID+"/" +
                                "batch_shop?user_id=235345536&code=0.5693125884203756" +
                                "&extras=%5B%22activities%22%2C%22albums%22%2C%22license%22%2C%22identification%22%2C%22qualification%22%5D&terminal" +
                                "=h5&latitude="+latitude+"&longitude="+longitude,dianID,longitude,latitude);


                   FileWriter fw = new FileWriter("C:\\"+shopname+"_"+"_"+dianmingstring, true);
                   BufferedWriter bw = new BufferedWriter(fw);
                   String s = FastJsonUtils.convertObjectToJSON(sj);
                   bw.write(s);
                   break;
                    }
                }

            }
            System.out.println("chongzhi ");
              shopname="";
              dzshopid="";
              address="";


        }







    }

    //设置城市为苏州，拿到收获地址的坐标
    public static List<Map> getzuobiao(String url) throws IOException {
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
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
    //在搜索框输入店名
    public static Map getdianmingID(String url, BigDecimal longitude,BigDecimal latitude) throws IOException {

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "*/*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", ele.COOKIE);
            httpGet.setHeader("referer","https://h5.ele.me/search/");
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            httpGet.setHeader("x-shard", "loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab", ele.X);
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //System.out.println("网页内容:"+result);
            System.out.println(url);
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
    //拿掉店的id之后，拿店的数据
    public static Map getdianshuju(String url, String id, BigDecimal longitude,BigDecimal latitude) throws IOException {

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":authority","h5.ele.me");
            httpGet.setHeader(":method","GET");
            httpGet.setHeader(":path",url.substring(17));
            httpGet.setHeader(":scheme", "https");
            httpGet.setHeader("accept", "application/json, text/plain, */*");
            httpGet.setHeader("accept-encoding", "gzip, deflate, br");
            httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("cookie", ele.COOKIE);
            httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
            httpGet.setHeader("x-shard", "shopid="+id+"loc="+longitude+","+latitude);
            httpGet.setHeader("x-uab", ele.X);
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
