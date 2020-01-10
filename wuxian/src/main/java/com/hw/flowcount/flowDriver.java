package com.hw.flowcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class flowDriver {
    public static void main(String[] args) throws  Exception {
        //1获取job
        Configuration conf=new Configuration();
        Job job=Job.getInstance(conf);
        //2加载驱动类
        job.setJarByClass(flowDriver.class);
        //3加载map和reduce
        job.setMapperClass(flowMapper.class);
        job.setReducerClass(flowReduce.class);
        //4设置mapper输出的key和value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(flowBean.class);
        //5.设置最终输出的k和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(flowBean.class);
        //6.设置输入和输出的路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[0]));

        //7.提交job

        boolean result=job.waitForCompletion(true);
        System.out.println(result?0:1);
    }
}
