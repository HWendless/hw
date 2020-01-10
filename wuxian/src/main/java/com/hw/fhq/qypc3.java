package com.hw.fhq;

import com.hw.cy.elesj;
import com.hw.cy.pojo.geographicalPosition;
import com.mao.spider.FastJsonUtils;
import com.mobiusvision.po.InfoEnterprise;
import com.mobiusvision.po.InfoPrincipalPersonnel;
import com.mobiusvision.po.InfoPropertyRightWebsite;
import com.mobiusvision.po.InfoShareholder;
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

public class qypc3 {
    public static void main(String[] args) throws Exception {
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

            //基本信息和股东信息
            String str = read(company[0] + "_基本信息和股东信息");
            Map map = FastJsonUtils.textTomap(str);
            String unified_social_credit_code = map.get("taxNo").toString();
            String entName=map.get("entName").toString();
            InfoEnterprise infoEnterprise = new InfoEnterprise();
            //经营范围
            infoEnterprise.setScopeofBusiness(map.get("scope").toString());
            //注册资本
            infoEnterprise.setRegisteredCapital(Float.parseFloat(map.get("regCapital").toString().replace("万(元)", "").replace(",", "")));
            //统一征信码（id）
            infoEnterprise.setUnifiedSocialCreditCode(unified_social_credit_code);
            //工商注册号
            if(map.containsKey("licenseNumber"))
            {
                infoEnterprise.setCompanyNumber(map.get("licenseNumber").toString());
            }

            //法定代表人
            infoEnterprise.setLegalRepresentative(map.get("legalPerson").toString());
            //登录机关
            infoEnterprise.setRegistrationAuthority(map.get("authority").toString());
            //企业类型
            infoEnterprise.setEnterpriseType(map.get("entType").toString());
            //行政区划
            infoEnterprise.setAdministrativeDivision(map.get("district").toString());
            //注册地址
            infoEnterprise.setRegistrationAddress(map.get("regAddr").toString());
            //营业状态
            infoEnterprise.setBusinessStatus(map.get("openStatus").toString());
            //所属行业
            infoEnterprise.setIndustry(map.get("industry").toString());
            //纳税人识别号
            infoEnterprise.setTaxpayerIdentificationNumber(unified_social_credit_code);
            //组织机构代码
            infoEnterprise.setOrganizationalCode(map.get("orgNo").toString());
            //成立日期
            infoEnterprise.setDateOfEstablishment(map.get("startDate").toString());
            //营业期限
            infoEnterprise.setBusinessTerm(map.get("openTime").toString());
            //annual_inspection_date年检
            infoEnterprise.setAnnualInspectionDate(map.get("annualDate").toString());
            //简介
            infoEnterprise.setDescribe(map.get("describe").toString());
            infoEnterprise.setTelephone(map.get("telephone").toString());
            infoEnterprise.setMailbox(map.get("email").toString());
            //公司名字
            infoEnterprise.setName(map.get("entName").toString());
            List<InfoEnterprise> saveinfoEnterprise=new ArrayList<InfoEnterprise>();
            saveinfoEnterprise.add(infoEnterprise);
            session.insert("insertInfoEnterprise",saveinfoEnterprise);
            //股东
            List<Map> shares=(List<Map>)map.get("shares");
            List<InfoShareholder> listshareholder=new ArrayList<InfoShareholder>();
            for(Map m:shares)
            {
                InfoShareholder infoShareholder=new InfoShareholder();
                infoShareholder.setShareholder(m.get("name").toString());//股东
                if(m.containsKey("amount"))
                {
                    infoShareholder.setContributionsPledged(m.get("amount").toString());//认缴出金额
                    infoShareholder.setActualContribution(m.get("amount").toString());//实际金额
                }
                infoShareholder.setActualContribution(m.get("type").toString());//类型
                infoShareholder.setUnifiedSocialCreditCode(unified_social_credit_code);
                listshareholder.add(infoShareholder);
            }
            //插入
            session.insert("insertlistshareholder",listshareholder);
            System.out.println(listshareholder);
            String str1 = read(company[0] + "_主要人员");
            Map map1 = FastJsonUtils.textTomap(str1);
            List<Map> personnellist=(List<Map>)map1.get("list");
            List<InfoPrincipalPersonnel> listInfoPrincipalPersonnel=new ArrayList<InfoPrincipalPersonnel>();
            for(Map m:personnellist)
            {
                InfoPrincipalPersonnel infoPrincipalPersonnel=new InfoPrincipalPersonnel();
                infoPrincipalPersonnel.setName(m.get("name").toString());
                infoPrincipalPersonnel.setPost(m.get("title").toString());
                infoPrincipalPersonnel.setUnifiedSocialCreditCode(unified_social_credit_code);
                listInfoPrincipalPersonnel.add(infoPrincipalPersonnel);
            }
            //插入
            System.out.println(listInfoPrincipalPersonnel);
            session.insert("insertInfoPrincipalPersonnel",listInfoPrincipalPersonnel);
            String str2 = read(company[0] + "_网站备案");
            Map map2 = FastJsonUtils.textTomap(str2);
            List<Map> homeSitelist=(List<Map>) map2.get("list");
            List<InfoPropertyRightWebsite> listinfoPropertyRightWebsite=new ArrayList<InfoPropertyRightWebsite>();
            for(Map m:homeSitelist)
            {
                //info_property_right_website
                InfoPropertyRightWebsite  infoPropertyRightWebsite=new InfoPropertyRightWebsite();
                List<String> l=(List<String>)m.get("homeSite");
                List<String> ll=(List<String>)m.get("domain");
                String homesite="";
                String domain="";
                for(String string:l)
                {
                    homesite +=string+",";
                }
                for(String string:ll)
                {
                    domain +=string+",";
                }
                infoPropertyRightWebsite.setDomainName(domain);
                infoPropertyRightWebsite.setHomeAddress(homesite);
                infoPropertyRightWebsite.setRecordNo(m.get("icpNo").toString());
                List<String> lL=(List<String>)m.get("domain");
                infoPropertyRightWebsite.setWebsiteAme(entName);
                infoPropertyRightWebsite.setRecordNo(m.get("icpNo").toString());
                infoPropertyRightWebsite.setUnifiedSocialCreditCode(unified_social_credit_code);
                listinfoPropertyRightWebsite.add(infoPropertyRightWebsite);
            }
            //插入
            if(listinfoPropertyRightWebsite.size()>0)
            {
                System.out.println(listinfoPropertyRightWebsite);
                session.insert("insertInfoPropertyRightWebsite",listinfoPropertyRightWebsite);
            }

//            for (String ss : name) {
//                // 1.解析出工商信息，股东（存集合）
//                System.out.println(ss);
//                if (ss.indexOf("基本信息和股东信息") != -1) {
//                }
//                if (ss.indexOf("主要人员") != -1) {
//                }
//                if (ss.indexOf("专利列表") != -1) {
//                }
//                if (ss.indexOf("网站备案") != -1) {
//                }
//            }
            System.out.println("----------------------" + (++index));
        }
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


}
