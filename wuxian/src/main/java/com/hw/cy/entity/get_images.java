package com.hw.cy.entity;

import com.hw.cy.other.IPBean;
import com.hw.cy.other.IPList;
import com.hw.cy.util.IPUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;

public class get_images {
    private static List<String> dz=new ArrayList<String>();
    public static synchronized void add(String s) {
//        ipBeanList.add(bean);
        dz.add(s);
    }
    public static void main(String[] args) throws  Exception {
        String s[]={"1","2","3","4","5","6","7","8","9","0"};
        String m[]={"q","w","e","r","t","y","u","i","o","p","a","s","d","f","f","g","h","j","k","l","z","x","c","v","b","n","m"};

        boolean kz=false;
        Set<String> lset=new HashSet<String>();


        //数字和字母的所有组合
        for(int i=0;i<s.length;i++)
        {
            for(int j=0;j<m.length;j++)
            {
                String lj=s[i]+m[j];
                String lj2=m[j]+s[i];
                lset.add(lj);
                lset.add(lj2);
            }
        }

        //数字和数字的所有组合
        for(int i=0;i<s.length;i++)
        {
            for(int j=i;j<s.length;j++)
            {
                lset.add(s[i]+s[j]);
                lset.add(s[j]+s[i]);
            }

        }
        //字母和字母的所有组合
        for(int i=0;i<m.length;i++)
        {
            for(int j=i;j<m.length;j++)
            {
                lset.add(m[i]+m[j]);
                lset.add(m[j]+m[i]);
            }

        }




            String A="";
            String B="";
           for( String ssss: s)
           {
               A=ssss;
               for(String mmmm: lset)
               {



                B=mmmm;

                   final String finalA = A;
                   final String finalB = B;
                   new Thread(new Runnable() {
                       public void run() {

                           try {
                               String ip="https://cube.elemecdn.com/"+ finalA +"/"+ finalB +"/f41ac0b7bc775c1a1048961af0c03jpeg.jpeg?";
                               if("200".equals(stdz(ip)))
                               {
                                   add(ip);
                               }
                           } catch (Exception e) {
                               e.printStackTrace();
                           }

                       }
                   }).start();
               }

           }

          for(String sssssss:dz)
          {
              System.out.println(dz);
          }


             //return document;


    }
    private  static String stdz(String ip) throws  Exception
    {




        CloseableHttpClient client = null;
        CloseableHttpResponse response = null;
            client = HttpClients.createDefault();

        URIBuilder uriBuilder = new URIBuilder(ip);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        response = client.execute(httpGet);

        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(response.getStatusLine());
        System.out.println("网页内容:"+result);
        Document document = Jsoup.parse(result);
         return response.getStatusLine().getStatusCode()+"";

    }
}
