package com.hw.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.net.URI;

public class hdfs {
    //上传文件到hdfs
    public static void main(String[] args) throws  Exception{
        putFileToHDFS();
    }

    public void initHDFS() throws Exception{
        // 1 创建配置信息对象
        Configuration configuration = new Configuration();

        // 2 获取文件系统
        FileSystem fs = FileSystem.get(configuration);

        // 3 打印文件系统
        System.out.println(fs.toString());
    }
    //获取文件系统
    //FileSystem
    //执行上传文件到hdfs
    public static void putFileToHDFS() throws Exception{
        // 1 创建配置信息对象
        // new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml
        // 然后再加载classpath下的hdfs-site.xml
        Configuration configuration = new Configuration();

        // 2 设置参数
        // 参数优先级： 1、客户端代码中设置的值  2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
        configuration.set("dfs.replication", "2");

        FileSystem fs = FileSystem.get(new URI("hdfs://172.30.7.124:8020"),configuration, "hadoop");

        // 3 创建要上传文件所在的本地路径
        Path src = new Path("E:\\hive测试\\emp.txt");

        // 4 创建要上传到hdfs的目标路径
        Path dst = new Path("hdfs://172.30.7.124:8020/user/hive/warehouse/emp.txt");

        // 5 拷贝文件
        fs.copyFromLocalFile(src, dst);
        fs.close();
    }


}
