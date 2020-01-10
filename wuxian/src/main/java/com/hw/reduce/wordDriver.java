package com.hw.reduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;
public class wordDriver {
    public static void main(String[] args) throws Exception{
        //获取job对象
        Configuration configuration=new Configuration();
        Job job=Job.getInstance(configuration);
        //加载驱动类
        job.setJarByClass(wordDriver.class);
        //加载mapper和reducer类
        job.setMapperClass(wordMapper.class);
        job.setReducerClass(wordReduce.class);
        //map端是输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入输出文件
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //提交
        boolean result=job.waitForCompletion(true);
        System.out.println(result?0:1);
    }
}
