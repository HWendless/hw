package com.hw.flowcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;


import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class flowMapper extends Mapper<LongWritable,Text,Text,flowBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line=context.toString();
        String fields[]=line.split("\\s+"); //用正则可按照一个或多个空格分割字符串
        // 3 取出手机号码
        String phoneNum = fields[1];

        // 4 取出上行流量和下行流量
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);

        // 5 写出数据
        context.write(new Text(phoneNum), new flowBean(upFlow, downFlow));

    }
}
