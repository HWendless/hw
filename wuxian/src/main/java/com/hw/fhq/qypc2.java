package com.hw.fhq;

import com.hw.cy.eleh5pc;
import com.hw.cy.elesj;
import com.hw.cy.entity.URLzh;
import com.hw.cy.pojo.geographicalPosition;
import com.mao.spider.FastJsonUtils;
import org.apache.avro.util.Utf8;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.nio.cs.ext.GBK;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class qypc2
{
    public static void main(String[] args) throws  Exception{
        String 后缀="&tot=xlTM-TogKuTwW-clK1HMuWxyv6ZLPTk14gmd&_=1567127796534";
        String y="https://xin.baidu.com/detail/basicAjax?pid=";
        String e="https://xin.baidu.com/detail/directorsAjax?pid=";
        String 对外投资="https://xin.baidu.com/detail/investajax?pid=";
        String 变更记录="https://xin.baidu.com/detail/changeRecordAjax?pid=";
        String san="https://xin.baidu.com/detail/patentAjax?pid=";
        String 专利详情="https://xin.baidu.com/detail/patentContentAjax?referId=20d5ef194e9ec7b577e3ff27e67bf3b3edd8d74b&_=1567133374313";
        String si="https://xin.baidu.com/detail/icpinfoAjax?pid=";
        String prefix="https://xin.baidu.com";
        String prefixarray []={y,e,san,si};
        String filename []={"基本信息和股东信息","主要人员","专利列表","网站备案"};
        //  https://xin.baidu.com/detail/lawWenshuAjax?pid=xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc*pAmd&p=1&tot=xlTM-TogKuTw4KKqNHN5Wj0Dcbmg7JGu4Qmd&_=1567075379526
        // https://xin.baidu.com/detail/basicAjax?pid=xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc*pAmd&tot=-KMTxgolTuTw4KKqNHN5Wj0Dcbmg7JGu4Qmd&_=1567127360096
        //  String s="https://xin.baidu.com/detail/directorsAjax?pid=xlTM-TogKuTwQLBHuRfXTLUW*aap5UmIUgmd&p=1&tot=xlTM-TogKuTwW-clK1HMuWxyv6ZLPTk14gmd&_=1567127796536";

        String pathname = "E:\\111.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        FileReader reader = new FileReader(pathname);
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line;

        while ((line = br.readLine()) != null) {

            // 一次读入一行数据
            String gsname=line;
//            String s="https://xin.baidu.com/s?q="+ URLzh.getURLEncoderString(gsname)+"&t=1";
//
//            String gxUrl="";
//
//            if(!StringUtils.isBlank(BaiduXin(s)))
//            {
//                String array[]=BaiduXin(s).split("pid=");
//
//
//                for(int i=0;i<prefixarray.length;i++)
//                {
//
//                    gxUrl= prefixarray[i]+array[1]+"&p=1";
//                    Map map=getqy(gxUrl,array[1]);
//                    if(map.containsKey("pageCount"))
//                    {
//                        for(int j=2;j<=Integer.parseInt(map.get("pageCount").toString());j++)
//                        {
//                            gxUrl= prefixarray[i]+array[1]+"&p="+j;
//                            //多页的数据
//                            Map maps=getqy(gxUrl,array[1]);
//                            writersj(gsname+"_"+filename[i]+j,maps.toString());
//                        }
//                        writersj(gsname+"_"+filename[i],map.toString());
//
//                    }
//                    //   System.out.println(map);
//                    writersj(gsname+"_"+filename[i],map.toString());
//                    if(i==2)
//                    {
//                        System.out.println(gxUrl);
//                    }
//                }
//            }


        }


       String dizhi="苏州碧利医疗科技有限公司,苏州康多机器人有限公司,苏州铸正机器人有限公司,苏州正交医学影像技术有限公司,苏州动影信息科技有限公司,苏州帝维达生物科技有限公司,苏州衡品医疗科技有限公司,苏州洛哈思信息科技有限公司,苏州瑞步康医疗科技有限公司,苏州信诺泰克医疗科技有限公司,苏州爱因医疗科技有限公司,苏州易奥秘光电科技有限公司,苏州奥根诺生物科技有限公司,苏州布芮恩科技有限公司,苏州瑞倍利康复科技有限公司,苏州心擎医疗技术有限公司,苏州众智初心健康科技有限公司,苏州理禾医疗技术有限公司,必胜途（苏州）工程科技有限公司";

     //   String dizhi="苏州瑞倍利康复科技有限公司,";
     //   String dizhi="苏州帝维达生物科技有限公司,苏州衡品医疗科技有限公司,苏州洛哈思信息科技有限公司,苏州瑞步康医疗科技有限公司,苏州信诺泰克医疗科技有限公司,苏州爱因医疗科技有限公司,苏州易奥秘光电科技有限公司,苏州奥根诺生物科技有限公司,苏州布芮恩科技有限公司,苏州瑞倍利康复科技有限公司,苏州心擎医疗技术有限公司,苏州众智初心健康科技有限公司,苏州理禾医疗技术有限公司,必胜途（苏州）工程科技有限公司";
        String dizhiarray[] =dizhi.split(",");
        for(int k=0;k<dizhiarray.length;k++)
        {
            String gsname=dizhiarray[k];
           // System.out.print(gsname+",");
            String s="https://xin.baidu.com/s?q="+gsname+"&t=1";
            String gxUrl="";
            if(!StringUtils.isBlank(BaiduXin(s)))
            {
                String array[]=BaiduXin(s).split("pid=");
                for(int i=0;i<prefixarray.length;i++)
                {
                    gxUrl= prefixarray[i]+array[1]+"&p=1";
                    Map map=getqy(gxUrl,array[1]);
                    if(map.containsKey("pageCount"))
                    {
                        for(int j=2;j<=Integer.parseInt(map.get("pageCount").toString());j++)
                        {
                            gxUrl= prefixarray[i]+array[1]+"&p="+j;
                            //多页的数据
                            Map maps=getqy(gxUrl,array[1]);
                            writersj(gsname+"_"+filename[i]+j,maps.toString());
                        }
                        writersj(gsname+"_"+filename[i],map.toString());
                    }
                    //   System.out.println(map);
                    writersj(gsname+"_"+filename[i],map.toString());
                }
            }

        }









    }
    public static Map getqy(String rul,String pid) throws IOException {
        try {
            Thread.sleep((int)(2000+Math.random()*(6000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String zx_history="ZX_HISTORY=%5B%7B%22visittime%22%3A%222019-08-29+17%3A34%3A38%22%2C%22pid%22%3A%22xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc%2ApAmd%22%7D%2C%7B%22visittime%22%3A%222019-08-29+17%3A06%3A22%22%2C%22pid%22%3A%22xlTM-TogKuTwQLBHuRfXTLUW%2Aaap5UmIUgmd%22%7D%5D"+pid;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            /*

            Accept-Language: zh-CN,zh;q=0.9
            Connection: keep-alive
            */
            httpGet.setHeader(":Accept","application/json, text/javascript, */*; q=0.01");
            httpGet.setHeader(":Accept-Encoding","gzip, deflate, br");
            httpGet.setHeader(":Accept-Language","zh-CN,zh;q=0.9");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie","PSTM=1565597631; BAIDUID=E0B625FDBEF569691D77A7A646272A6F:FG=1; BIDUPSID=C55062EA9BA49402376BB195FE6A48D3; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598;\n" +
                    " BDPPN=7fa923e6d415593140ef34df8c877b4f; log_guid=474b4d3b3d135c2c2bf7b6eaf18309c6; ZX_UNIQ_UID=cbc86230638709c9b8e307589d200846;\n" +
                    " __cas__st__=NLI; __cas__id__=0; Hm_lvt_baca6fe3dceaf818f5f835b0ae97e4cc=1567064173,1567064606,1567124089;\n" +
                    " H_PS_PSSID=26525_1462_21078_29522_29520_29720_29568_29220_29071_22160; delPer=0; PSINO=5;\n" +
                    " locale=zh; __yjsv5_shitong=1.0_7_8e85d614896e7cb3c07df88c49bc20d74f7e_300_1567126768456_218.4.150.14_42988754;\n" +
                    " yjs_js_security_passport=83a17274c19cd424986a9f88f5c55cb80ad16b72_1567126769_js;\n" +
                    " ZX_HISTORY=%5B%7B%22visittime%22%3A%222019-08-30+09%3A35%3A24%22%2C%22pid%22%3A%22\n" +
                    " xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc%2ApAmd%22%7D%2C%7B%22visittime%22%3A%222019-08-30+09%3A16%3A40%22%2C%22pid%22%3A%22xlTM-TogKuTwQLBHuRfXTLUW%2Aaap5UmIUgmd%22%7D%5D;\n" +
                    " Hm_lpvt_baca6fe3dceaf818f5f835b0ae97e4cc=1567128921");
            httpGet.setHeader("Host", "xin.baidu.com");
            httpGet.setHeader("Referer", "https://xin.baidu.com//detail/compinfo?pid=xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc*pAmd&tab=basic");
            httpGet.setHeader("User-Agent", elesj.getuseragent());
            httpGet.setHeader("X-Requested-With", "XMLHttpRequest");
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
           // System.out.println("网页内容:"+result);
            List<Map> maps=new ArrayList<Map>();
            Map map= FastJsonUtils.textTomap(result);
            map.get("data");
            return  (Map) map.get("data");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            response.close();
            client.close();
        }
    }
    public static String BaiduXin(String rul) throws IOException {
        try {
            Thread.sleep((int)(2000+Math.random()*(6000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            System.out.println("###########::"+rul);
            httpGet.setHeader(":Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpGet.setHeader(":Accept-Encoding","gzip, deflate, br");
            httpGet.setHeader(":Cache-Control","max-age=0");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie","PSTM=1565597631; BAIDUID=E0B625FDBEF569691D77A7A646272A6F:FG=1; BIDUPSID=C55062EA9BA49402376BB195FE6A48D3; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BDPPN=7fa923e6d415593140ef34df8c877b4f; log_guid=474b4d3b3d135c2c2bf7b6eaf18309c6; ZX_UNIQ_UID=cbc86230638709c9b8e307589d200846; yjs_js_security_passport=609de8a66beccfdf2c0ce6367cfcf4f5d9d54b8a_1567065826_js; delPer=0; PSINO=5; __cas__st__=NLI; __cas__id__=0; Hm_lvt_baca6fe3dceaf818f5f835b0ae97e4cc=1567064173,1567064606,1567124089; ZX_HISTORY=%5B%7B%22visittime%22%3A%222019-08-30+08%3A21%3A09%22%2C%22pid%22%3A%22xlTM-TogKuTwQLBHuRfXTLUW%2Aaap5UmIUgmd%22%7D%2C%7B%22visittime%22%3A%222019-08-29+18%3A57%3A50%22%2C%22pid%22%3A%22xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc%2ApAmd%22%7D%5D; H_PS_PSSID=1462_21078_29522_29520_29720_29568_29220_29071_22160; Hm_lpvt_baca6fe3dceaf818f5f835b0ae97e4cc=1567125301");
            httpGet.setHeader("Host", "xin.baidu.com");
            httpGet.setHeader("Upgrade-Insecure-Requests", "1");
            httpGet.setHeader("User-Agent", elesj.getuseragent());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            //    System.out.println("网页内容:"+result);
            Document document = Jsoup.parse(result);
            Elements links = document.select("a[href]");
            for (Element e:links)
            {

                String strPrjUrl=e.attr("href");
                if(strPrjUrl.indexOf("pid")!=-1)
                {

                    return strPrjUrl;
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("yicheng"+rul);
            return null;
        } finally {
            response.close();
            client.close();
        }
    }


    public static void writersj(String name , String nr) throws IOException {
        FileWriter fw = new FileWriter("E:\\1234\\"+name);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nr);
        bw.close();

    }
}
