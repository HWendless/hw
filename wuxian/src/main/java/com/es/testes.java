package com.es;


import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class testes {
    public static void main(String[] args) throws  Exception{
        Settings settings= Settings.builder().put("cluster.name","my-elasticsearch").build();
        System.out.println(InetAddress.getByName("127.0.0.1"));
        TransportClient client = new PreBuiltTransportClient(settings);
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.60.79"), 9200));
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.60.79"), 9201));
        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.60.79"), 9202));
         //创建名称为blog2的索引
         client.admin().indices().prepareCreate("blog2").get();
         //释放资源
         client.close();
            }
}
