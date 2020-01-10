package com.hw.reduce;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//map阶段
/*
KEYIN  map的输入LongWritable 行号
VALUEIN map的values输入 text一行内容
KEYOUT  map的输出 text 单词
VALUEOUT map的输出  intwritable  单词的个数
* */
public class wordMapper extends Mapper<LongWritable,Text, Text, IntWritable>{
    //定义输出的类型
    Text text=new Text(); //输出的key
    IntWritable w=new IntWritable();
    @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String L=value.toString();
        String larray[]= L.split(" ");

        for(String s:larray)
        {
            text.set(s);
            w.set(1);
            context.write(text,w);
        }
       //   `
    }
}
