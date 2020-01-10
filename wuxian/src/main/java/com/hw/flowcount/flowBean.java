package com.hw.flowcount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class flowBean implements Writable {
    private long upFlow; //上行流量
    private long downflow;     //下行流量
    private long sumFlow; //总流量

    //空参构造必须有
    public flowBean() {
        super();
    }

    public flowBean(long upFlow, long downflow) {
        this.upFlow = upFlow;
        this.downflow = downflow;
        this.sumFlow=upFlow+downflow;
    }

    public void  set(long upFlow, long downflow) {
        this.upFlow = upFlow;
        this.downflow = downflow;
        this.sumFlow=upFlow+downflow;
    }

    //序列化
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downflow);
        out.writeLong(sumFlow);
    }
    //反序列化,必须和序列化方法顺序一致
    public void readFields(DataInput in) throws IOException {
        upFlow=in.readLong();
        downflow=in.readLong();
        sumFlow=in.readLong();
    }

    @Override
    public String toString() {
        return
                "upFlow=" +"\t"+ upFlow +
                ", downflow=" +"\t"+ downflow +
                ", sumFlow="+"\t" + sumFlow ;
    }


    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownflow() {
        return downflow;
    }

    public void setDownflow(long downflow) {
        this.downflow = downflow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }
}
