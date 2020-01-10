package com.hw.flowcount;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/*
* 输入对应mapper的输出
* 输出的格式就是想要得到的格式
* */
public class flowReduce extends Reducer<Text,flowBean,Text, flowBean>{
    flowBean v=new flowBean();
    @Override
    protected void reduce(Text key, Iterable<flowBean> values, Context context) throws IOException, InterruptedException {
        long  sum_upflow=0;
        long sum_downflow=0;

        //汇总
        for(flowBean f:values)
        {
            sum_upflow +=f.getSumFlow();
            sum_downflow +=f.getDownflow();
        }

        //添加数据
        v.set(sum_upflow,sum_downflow);
        //输出
        context.write(key,v);

    }
}
