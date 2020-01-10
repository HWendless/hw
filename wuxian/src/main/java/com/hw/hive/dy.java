package com.hw.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dy {
    private static String driver = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://172.30.7.124:10000/default";
    private static String user = "hadoop"; //一般情况下可以使用匿名的方式，在这里使用了root是因为整个Hive的所有安装等操作都是root
    private static String password = "";
    //导入txt文件
    public static void main1(String[] args) {
        ResultSet res = null;

        try {
            /**
             * 第一步：把JDBC驱动通过反射的方式加载进来
             */
            Class.forName(driver);

            /**
             * 第二步：通过JDBC建立和Hive的连接器，默认端口是10000，默认用户名和密码都为空
             */
            Connection conn = DriverManager.getConnection(url, user, password);

            /**
             * 第三步：创建Statement句柄，基于该句柄进行SQL的各种操作；
             */
            Statement stmt = conn.createStatement();

            /**
             * 接下来就是SQL的各种操作；
             * 第4.1步骤：建表Table,如果已经存在的话就要首先删除；
             */
            String tableName = "testHiveDriverTable";
            stmt.execute("drop table if exists " + tableName );


       //     stmt.execute("create table " + tableName + " (id int, name string)" + "ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'");
            stmt.execute("create table " + tableName + " (id int, name string)" + "ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'");
            /**
             *  第4.2步骤：查询建立的Table；
             */
            String sql = "show tables '" + tableName + "'";
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);
            if (res.next()) {
                System.out.println(res.getString(1));
            }
            /**
             *  第4.3步骤：查询建立的Table的schema；
             */
            sql = "describe " + tableName;
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2));
            }

            /**
             *  第4.4步骤：加载数据进入Hive中的Table；
             */
           // String filepath = "/root/Documents/data/sql/testHiveDriver.txt";

            String filepath = "/opt/module/datas/student.txt";
            sql = "load data local inpath '" + filepath + "' into table " + tableName;
            System.out.println("Running: " + sql);
            stmt.execute(sql);

            /**
             *  第4.5步骤：查询进入Hive中的Table的数据；
             */
            sql = "select * from " + tableName;
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
            }

            /**
             *  第4.6步骤：Hive中的对Table进行统计操作；
             */
            sql = "select count(1) from " + tableName;   //在执行select count(*) 时候会生成mapreduce 操作  ，那么需要启动资源管理器 yarn  ： start-yarn.sh
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);

            while (res.next()) {
                System.out.println("Total lines :" + res.getString(1));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //导入json文件
    public static void main(String[] args) {
        ResultSet res = null;

        try {
            /**
             * 第一步：把JDBC驱动通过反射的方式加载进来
             */
            Class.forName(driver);

            /**
             * 第二步：通过JDBC建立和Hive的连接器，默认端口是10000，默认用户名和密码都为空
             */
            Connection conn = DriverManager.getConnection(url, user, password);

            /**
             * 第三步：创建Statement句柄，基于该句柄进行SQL的各种操作；
             */
            Statement stmt = conn.createStatement();

            /**
             * 接下来就是SQL的各种操作；
             * 第4.1步骤：建表Table,如果已经存在的话就要首先删除；
             */
            String tableName = "testHiveDriverTablejson111";
            stmt.execute("drop table if exists " + tableName );


            //     stmt.execute("create table " + tableName + " (id int, name string)" + "ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' LINES TERMINATED BY '\n'");
//            create table spark_people_json(
//
//                    `name` string,
//
//                    `age`   int)
//
//            ROW FORMAT SERDE 'org.apache.hive.hcatalog.data.JsonSerDe'
//
//            STORED AS TEXTFILE;
           // stmt.execute("create table " + tableName + " (id int, name string)" + "ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t'");
//            CREATE TABLE tmp_json_mapping (
//                    country string,
//                    dt string
//            )
//            ROW FORMAT SERDE 'org.openx.data.jsonserde.JsonSerDe'
//            STORED AS TEXTFILE;
            stmt.execute("create table " + tableName + " (name string, age int,sex string )" + "ROW FORMAT SERDE 'org.openx.data.jsonserde.JsonSerDe'" +
                    "STORED AS TEXTFILE");
            /**
             *  第4.2步骤：查询建立的Table；
             */
            String sql = "show tables '" + tableName + "'";
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);
            if (res.next()) {
                System.out.println(res.getString(1));
            }
            /**
             *  第4.3步骤：查询建立的Table的schema；
             */
            sql = "describe " + tableName;
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2));
            }

            /**
             *  第4.4步骤：加载数据进入Hive中的Table；
             */
            // String filepath = "/root/Documents/data/sql/testHiveDriver.txt";

//            String filepath = "/opt/module/datas/student.txt";
//            sql = "load data local inpath  '" + filepath + "' into table " + tableName;
            String filepath = "/user/hive/warehouse/testhw.json";
            sql = "load data  inpath '" + filepath + "' into table " + tableName;
            System.out.println("Running: " + sql);
            stmt.execute(sql);

            /**
             *  第4.5步骤：查询进入Hive中的Table的数据；
             */
            sql = "select * from " + tableName;
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);
            while (res.next()) {
                System.out.println(String.valueOf(res.getString(1)) + "\t" + res.getString(2)+"\t" + res.getString(3));
            }

            /**
             *  第4.6步骤：Hive中的对Table进行统计操作；
             */
            sql = "select count(1) from " + tableName;   //在执行select count(*) 时候会生成mapreduce 操作  ，那么需要启动资源管理器 yarn  ： start-yarn.sh
            System.out.println("Running: " + sql);
            res = stmt.executeQuery(sql);

            while (res.next()) {
                System.out.println("Total lines :" + res.getString(1));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
