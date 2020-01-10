 package com.hw.flow;




import com.hw.cy.entity.CREATE_UUID;
import com.mobiusvision.po.InfoEnterprise;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class streamflow {
    public static void main(String[] args) {
//        User u1=new User(11,"a",23);
//        User u2=new User(12,"b",24);
//        User u3=new User(13,"c",22);
//        User u4=new User(14,"d",28);
//        User u5=new User(16,"e",26);
//        List<User> list= Arrays.asList(u1,u2,u3,u4,u5);
//        list.stream().filter(u ->{return  u.getId()%2==0;}).filter(u ->{return u.getAge()>24;}).forEach(System.out::println);
//        List<Integer > list2=Arrays.asList(1,2,3);
//        list2=list2.stream().map(u ->{ return u*2;}).collect(Collectors.toList());
//        list2.forEach(System.out::println);
//


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
        LocalDateTime time=LocalDateTime. now();
        for(int i=0;i<10000;i++)
        {
             time= time.minusSeconds(1);
            AuthRecentlyUsed authRecentlyUsed=new AuthRecentlyUsed();
            authRecentlyUsed.setAppCode("sdfdsfd");
            authRecentlyUsed.setAppId("fhsfg");
            authRecentlyUsed.setId(CREATE_UUID.getId());
            authRecentlyUsed.setMainPageUrl("http://");
            authRecentlyUsed.setUserID("123");
            authRecentlyUsed.setPerationTime(time);
            int j=session.insert("insert_auth_recently_used",authRecentlyUsed);
        }


        //传入参数查询，返回结果
        //        User user=session.selectOne("findById",1);
        //输出结果
        //     System.out.println(user.getUname());
        //关闭session



    }


//    public static void main(String[] args) {
//        //获取前一天的时间
//        LocalDateTime time=LocalDateTime. now();
//        LocalDateTime time2= time.minusSeconds(1);
//        System. out .println(time );
//        System. out .println(time2 );
//        getTime();
//    }
//
//    private static void getTime(){
//        Calendar calendar =Calendar. getInstance();
//        calendar.add( Calendar. DATE, -1); //向前走一天
//        Date date= calendar.getTime();
//        System. out .println("前一天时间为" +date .toString());
//    }


}
