package com.hw.fhq;

import com.hw.cy.elesj;
import com.hw.cy.pojo.geographicalPosition;
import com.mao.spider.FastJsonUtils;
import com.mobiusvision.po.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.xmlbeans.impl.jam.mutable.MPackage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class qypc4 {
    public static void main(String[] args) throws Exception {
        List<InfoPropertyRightPatent> crlist=new ArrayList<InfoPropertyRightPatent>();
        String zlxq="https://xin.baidu.com/detail/patentContentAjax?referId=";
        //数据库操作
        //定义读取文件名
        String resources = "mybatis-config.xml";
        //创建流
        Reader reader = null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader = Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        //创建session实例
        SqlSession session = sqlMapper.openSession();
        List<InfoEnterprise> list=session.selectList("selectall");
        System.out.println(list);
        //传入参数查询，返回结果
        //        User user=session.selectOne("findById",1);
        //输出结果
        //     System.out.println(user.getUname());
        //关闭session



        String dizhi = "苏州碧利医疗科技有限公司,苏州康多机器人有限公司,苏州铸正机器人有限公司,苏州正交医学影像技术有限公司,苏州动影信息科技有限公司,苏州帝维达生物科技有限公司," +
                "苏州衡品医疗科技有限公司,苏州洛哈思信息科技有限公司,苏州瑞步康医疗科技有限公司,苏州信诺泰克医疗科技有限公司,苏州爱因医疗科技有限公司," +
                "苏州易奥秘光电科技有限公司,苏州奥根诺生物科技有限公司,苏州布芮恩科技有限公司,苏州瑞倍利康复科技有限公司,苏州心擎医疗技术有限公司," +
                "苏州众智初心健康科技有限公司,苏州理禾医疗技术有限公司,必胜途（苏州）工程科技有限公司";
        String dizhiarray[] = dizhi.split(",");
        //获取数据文件名集合
        List<String> filenamelist = readfilename();
        int index = 0;
        for (String s : dizhiarray) {
            List<String> name = new ArrayList<String>();
            for (int i = 0; i < filenamelist.size(); i++) {
                //
                if (filenamelist.get(i).indexOf(s) != -1 && filenamelist.get(i).indexOf("专利列表") != -1) {
                    name.add(filenamelist.get(i));
                }
            }
            String company[] = name.get(0).split("_");

            for(String sss:name)
            {
//                System.out.println(sss);
                Map map = FastJsonUtils.textTomap(read(sss));
                List<Map> listzl =(List<Map>) map.get("list");
                for (Map m:listzl)
                {
                    InfoPropertyRightPatent infoPropertyRightPatent=new InfoPropertyRightPatent();
                   String referid= m.get("referId").toString();
                    Map map1=getshuju(zlxq+referid);
                    //名称
                    infoPropertyRightPatent.setName(map1.get("patentName").toString());
                    //专利申请人
                    infoPropertyRightPatent.setProfessionalApplicants(map1.get("entName").toString());
                    //类型
                    infoPropertyRightPatent.setType(map1.get("patentType").toString());
                    //申请号
                    infoPropertyRightPatent.setApplicationNumber(map1.get("patentAppNumber").toString());
                    //申请日
                    infoPropertyRightPatent.setFilingDate(map1.get("applicationDate").toString());
                    //申请公布号
                    infoPropertyRightPatent.setApplicationPublicationNumber(map1.get("publicationNumber").toString());
                    //申请公布日
                    infoPropertyRightPatent.setApplicationPublicationDate(map1.get("publicationDate").toString());
                    //法律历史状态
                    infoPropertyRightPatent.setLegalHistory(map1.get("patentLegalStatusHistory").toString());
                    //摘要
                    infoPropertyRightPatent.setDetails(map1.get("patentAbstract").toString());
                    //发明人
                    infoPropertyRightPatent.setInventor(map1.get("inventorNameList").toString());
                    //专业代理机构
                    infoPropertyRightPatent.setProfessionalGency(map1.get("patentAgencyName").toString());
                    //专业代理人
                    infoPropertyRightPatent.setProfessionalAgent(map1.get("patentAgencyPerson").toString());
                    //法律状态
                    infoPropertyRightPatent.setLegalStatus(map1.get("patentLegalStatus").toString());
                    crlist.add(infoPropertyRightPatent);
                }


            }
            System.out.println("----------------------" + (++index));

        }
        for(int i=0;i<crlist.size();i++)
        {
            for(InfoEnterprise e:list)
            {
                if(crlist.get(i).getProfessionalApplicants().equals(e.getName()))
                {
                    crlist.get(i).setUnifiedSocialCreditCode(e.getUnifiedSocialCreditCode());
                    break;
                }
            }
        }
        session.insert("insertinfoPropertyRightPatent",crlist);
        session.commit();
        session.close();

        //  2.主要成员（存集合）
        //  3.网站
        //  4.拿到专利列表获取专利的数据
        //


    }

    public static List<String> readfilename() throws Exception {
        List<String> lstring = new ArrayList<String>();
        //   String URL="https://xin.baidu.com/detail/patentContentAjax?referId=";
        File file = new File("E:\\1234");
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                    } else {
                        //System.out.println("文件:" + file2.getAbsolutePath());
                        String pathname = file2.getAbsolutePath(); // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
                        lstring.add(pathname);
                    }
//                    }
                }

            }
        } else {
            System.out.println("文件不存在!");
        }
        return lstring;
    }


    public static Map getzl(String rul) throws IOException {
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();

            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            /*
            Connection: keep-alive
            Cookie:


            *
            * */
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            httpGet.setHeader("Accept-Encoding", "gzip, deflate, br");
            httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpGet.setHeader("Cache-Control", "max-age=0");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", "PSTM=1565597631; BAIDUID=E0B625FDBEF569691D77A7A646272A6F:FG=1; BIDUPSID=C55062EA9BA49402376BB195FE6A48D3; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BDPPN=7fa923e6d415593140ef34df8c877b4f; log_guid=474b4d3b3d135c2c2bf7b6eaf18309c6; ZX_UNIQ_UID=cbc86230638709c9b8e307589d200846; __cas__st__=NLI; __cas__id__=0; yjs_js_security_passport=83a17274c19cd424986a9f88f5c55cb80ad16b72_1567126769_js; delPer=0; ZD_ENTRY=baidu; PSINO=5; H_PS_PSSID=26525_1462_21078_29522_29520_29720_29568_29220_29071_22160; Hm_lvt_baca6fe3dceaf818f5f835b0ae97e4cc=1567064173,1567064606,1567124089,1567150016; ZX_HISTORY=%5B%7B%22visittime%22%3A%222019-08-30+15%3A29%3A01%22%2C%22pid%22%3A%22xlTM-TogKuTwQLBHuRfXTLUW%2Aaap5UmIUgmd%22%7D%2C%7B%22visittime%22%3A%222019-08-30+15%3A26%3A58%22%2C%22pid%22%3A%22xlTM-TogKuTwelvw9Gglj4fRkRhn1deMvgmd%22%7D%2C%7B%22visittime%22%3A%222019-08-30+14%3A47%3A19%22%2C%22pid%22%3A%22xlTM-TogKuTwS0bgg5GcfNgvCEcWHkUw4Amd%22%7D%2C%7B%22visittime%22%3A%222019-08-30+14%3A21%3A50%22%2C%22pid%22%3A%22xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc%2ApAmd%22%7D%2C%7B%22visittime%22%3A%222019-08-30+10%3A14%3A03%22%2C%22pid%22%3A%22xlTM-TogKuTwbPJUamsOsec8WlRteQ4vQwmd%22%7D%5D; Hm_lpvt_baca6fe3dceaf818f5f835b0ae97e4cc=1567150138");
            httpGet.setHeader("Host", "xin.baidu.com");
            httpGet.setHeader("Upgrade-Insecure-Requests", "1");
            httpGet.setHeader("User-Agent", elesj.getuseragent());
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            // System.out.println("网页内容:"+result);
            List<Map> maps = new ArrayList<Map>();
            Map map = FastJsonUtils.textTomap(result);
            map.get("data");
            return (Map) map.get("data");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("yicheng" + rul);
            return null;
        } finally {
            response.close();
            client.close();
        }
    }

    public static void writersj(String name, String nr) throws IOException {
        FileWriter fw = new FileWriter("E:\\1234\\" + name);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(nr);
        bw.close();

    }

    public static String read(String name) throws Exception {
        FileReader reader = new FileReader(name);
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        return br.readLine().toString();
    }

    public static Map getshuju(String rul) throws Exception {
       // String zx_history="ZX_HISTORY=%5B%7B%22visittime%22%3A%222019-08-29+17%3A34%3A38%22%2C%22pid%22%3A%22xlTM-TogKuTw5ZrKv2bGXM8i3sDinhc%2ApAmd%22%7D%2C%7B%22visittime%22%3A%222019-08-29+17%3A06%3A22%22%2C%22pid%22%3A%22xlTM-TogKuTwQLBHuRfXTLUW%2Aaap5UmIUgmd%22%7D%5D"+pid;
        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
        try {

            client = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder(rul);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader(":Accept","text/html, application/xhtml+xml, application/xml; q=0.9, */*; q=0.8");
            httpGet.setHeader(":Accept-Encoding","gzip, deflate, br");
            httpGet.setHeader(":Accept-Language","zh-CN");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("Cookie", "__cas__st__=NLI; __cas__id__=0");
            httpGet.setHeader("Host", "xin.baidu.com");
            httpGet.setHeader("Upgrade-Insecure-Requests", "1");
            httpGet.setHeader("User-Agent", elesj.getuseragent());
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


}
