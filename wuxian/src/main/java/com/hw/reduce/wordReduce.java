package com.hw.reduce;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/*
reduce阶段，输入类型对应mapper的输出类型
KEYIN     text  单词
VALUEIN,  IntWritable 单词个数
KEYOUT ,  Text 单词
VALUEOUT  IntWritable单词个数
* */

public class wordReduce extends Reducer<Text,IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count=0;
        //汇总
        for(IntWritable value:values)
        {
            count +=value.get();
        }

        //输出
        context.write(key,new IntWritable(count));

    }
}
