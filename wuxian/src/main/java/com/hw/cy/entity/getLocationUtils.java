package com.hw.cy.entity;

import com.alibaba.fastjson.JSONArray;
import com.hw.cy.pojo.geographicalPosition;
import com.mao.spider.FastJsonUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class getLocationUtils {
    public static void main(String[] args) throws  Exception{
        int i=0;
        List<geographicalPosition> list=getcs();
        for(geographicalPosition p :list)
        {
            p.getssqj();
            p.getlnglat();
            System.out.println( p.getssqj());
            System.out.println(p.getlnglat());
            i++;
        }
        System.out.println(i);
    }
    public static List<geographicalPosition> getcs() throws  Exception{
        List<geographicalPosition> glist=new ArrayList<geographicalPosition>();
        List l=new ArrayList();
        String pathname = "e:\\save.json"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        FileReader reader = new FileReader(pathname);
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line;

        while ((line = br.readLine()) != null) {
            // 一次读入一行数据

            l.add(line);
        }



        //  List<Map> maps = FastJsonUtils.toList(l.toString(), Map.class); stringToCollect
        // Map map=FastJsonUtils.stringToCollect(l.get(0).toString());  toList
        Map map=new HashMap();
        //   List list=new ArrayList();
        Object o= FastJsonUtils.textToJson(l.get(0).toString());
        JSONArray J=(JSONArray) o;
        //国家名称
        String countryname="";
        //省名称
        String provincename="";
        //市名称
        String cityname="";
        //区域名称
        String district="";
        //街道名称
        String street="";
        //国家坐标
        String countrylng="";
        String countrylat="";
        String countrylnglat="";
        //省坐标
        String provincelng="";
        String provincelat="";
        String provincelnglat="";
        //市坐标
        String citylng="";
        String citylat="";
        String citylnglat="";
        //区域坐标
        String districtlng="";
        String districtlat="";
        String districtlnglat="";
        //街道坐标
        String streetlng="";
        String streetlat="";
        String streetlnglat="";
        Map m=FastJsonUtils.textTomap(J.get(0).toString());
        //拿到国下面的省集合
        List districtList=(List)m.get("districtList");
        //拿到国中心坐标
        Map centermap=(Map)m.get("center");
        //拿到名字
        String name=m.get("name").toString();
        //国标识
        String level=m.get("level").toString();
        //省集合遍历
        for(int i=0;i<districtList.size();i++)
        {
            //拿到国下面的省
            Map ms=(Map) districtList.get(i);
            Map centermap1=(Map)ms.get("center");
            //拿到省名字
            String name1=ms.get("name").toString();
            String level1=ms.get("level").toString();
            List districtLists=(List)ms.get("districtList");
            if(districtLists!=null)
            {
                for(int j=0;j<districtLists.size();j++) {

                    //拿到市下面的区
                    Map msq = (Map) districtLists.get(j);
                    Map centermap11 = (Map) msq.get("center");
                    //拿到市名字
                    String name11 = msq.get("name").toString();
                    String level11 = msq.get("level").toString();
                    List districtListss = (List) msq.get("districtList");
                    if(districtListss!=null)
                    {
                        for (int k = 0; k < districtListss.size(); k++) {
                            //拿到区下面的
                            Map msqj = (Map) districtListss.get(k);
                            Map centermap111 = (Map) msqj.get("center");
                            //拿到区名字
                            String name111 = msqj.get("name").toString();
                            String level111 = msqj.get("level").toString();
                            List districtListsss = (List) msqj.get("districtList");
                            if(districtListsss!=null)
                            {
                                for (int w = 0; w < districtListsss.size(); w++) {
                                    //拿到区下面的街
                                    Map msqjj = (Map) districtListsss.get(w);
                                    Map centermap1111 = (Map) msqjj.get("center");
                                    //拿到区名字
                                    String name1111 = msqjj.get("name").toString();
                                    String level1111 = msqjj.get("level").toString();


                                    geographicalPosition g=new geographicalPosition();
                                    //国家
                                    g.setCountryname(name);
                                    g.setCountrylng(centermap.get("lng").toString());
                                    g.setCountrylat(centermap.get("lat").toString());
                                    g.setCountrylnglat(centermap.get("lng").toString()+","+centermap.get("lat").toString());
//                provincename
                                    //省
                                    g.setProvincename(name1);
                                    g.setProvincelat(centermap1.get("lat").toString());
                                    g.setProvincelng(centermap1.get("lng").toString());
                                    g.setProvincelnglat(centermap1.get("lng").toString()+","+centermap1.get("lat").toString());
                                    //市
//                        cityname
                                    g.setCityname(name11);
                                    g.setCitylat(centermap11.get("lat").toString());
                                    g.setCitylng(centermap11.get("lng").toString());
                                    g.setCitylnglat(centermap11.get("lng").toString()+","+centermap11.get("lat").toString());
                                    //区
                                    g.setDistrictname(name111);
                                    g.setDistrictlat(centermap111.get("lat").toString());
                                    g.setDistrictlng(centermap111.get("lng").toString());
                                    g.setDistrictlnglat(centermap111.get("lng").toString()+","+centermap111.get("lat").toString());
                                    //街道
                                    g.setStreetname(name1111);
                                    g.setStreetlat(centermap1111.get("lat").toString());
                                    g.setStreetlng(centermap1111.get("lng").toString());
                                    g.setStreetlnglat(centermap1111.get("lng").toString()+","+centermap1111.get("lat").toString());

                                    g.setLevel(level1111);
                                    glist.add(g);
                                }
                            }
                            else {

                                geographicalPosition g=new geographicalPosition();
                                //国家
                                g.setCountryname(name);
                                g.setCountrylng(centermap.get("lng").toString());
                                g.setCountrylat(centermap.get("lat").toString());
                                g.setCountrylnglat(centermap.get("lng").toString()+","+centermap.get("lat").toString());
//                provincename
                                //省
                                g.setProvincename(name1);
                                g.setProvincelat(centermap1.get("lat").toString());
                                g.setProvincelng(centermap1.get("lng").toString());
                                g.setProvincelnglat(centermap1.get("lng").toString()+","+centermap1.get("lat").toString());
                                //市
//                        cityname
                                g.setCityname(name11);
                                g.setCitylat(centermap11.get("lat").toString());
                                g.setCitylng(centermap11.get("lng").toString());
                                g.setCitylnglat(centermap11.get("lng").toString()+","+centermap11.get("lat").toString());
                                //区
                                g.setDistrictname(name111);
                                g.setDistrictlat(centermap111.get("lat").toString());
                                g.setDistrictlng(centermap111.get("lng").toString());
                                g.setDistrictlnglat(centermap111.get("lng").toString()+","+centermap111.get("lat").toString());

                                g.setLevel(level111);

                                glist.add(g);
                            }

                        }

                    }
                    else {
                        geographicalPosition g=new geographicalPosition();
                        //国家
                        g.setCountryname(name);
                        g.setCountrylng(centermap.get("lng").toString());
                        g.setCountrylat(centermap.get("lat").toString());
                        g.setCountrylnglat(centermap.get("lng").toString()+","+centermap.get("lat").toString());
//                provincename
                        //省
                        g.setProvincename(name1);
                        g.setProvincelat(centermap1.get("lat").toString());
                        g.setProvincelng(centermap1.get("lng").toString());
                        g.setProvincelnglat(centermap1.get("lng").toString()+","+centermap1.get("lat").toString());
                        //市
//                        cityname
                        g.setCityname(name11);
                        g.setCitylat(centermap11.get("lat").toString());
                        g.setCitylng(centermap11.get("lng").toString());
                        g.setCitylnglat(centermap11.get("lng").toString()+","+centermap11.get("lat").toString());
                        //类型
                        g.setLevel(level11);
                        glist.add(g);
                    }

                }
            }else{
                geographicalPosition g=new geographicalPosition();
                //国家
                g.setCountryname(name);
                g.setCountrylng(centermap.get("lng").toString());
                g.setCountrylat(centermap.get("lat").toString());
                g.setCountrylnglat(centermap.get("lng").toString()+","+centermap.get("lat").toString());
//                provincename
                //省
                g.setProvincename(name1);
                g.setProvincelat(centermap1.get("lat").toString());
                g.setProvincelng(centermap1.get("lng").toString());
                g.setProvincelnglat(centermap1.get("lng").toString()+","+centermap1.get("lat").toString());
                //类型
                g.setLevel(level1);
                glist.add(g);
            }


        }




        // return l;

//        for(geographicalPosition p : glist)
//        {
//            System.out.println(p);
//        }
//        System.out.println(glist.size());
        return  glist;
    }
}
