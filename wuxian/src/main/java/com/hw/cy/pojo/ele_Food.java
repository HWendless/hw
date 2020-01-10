package com.hw.cy.pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ele_Food {
    //原料
    private  String description;
    //名字
    private  String foodname;
    //月销量
    private Integer foodmonth_sales;
    //吃的评分
    private Double foodrating;
    //吃的热度
    private  Double satisfy_rate;
    //单品最低价
    private  Integer lowest_price;
    //月份
    private  String insertdata;
    //食物类型
    private String foodtype;
    //商店id
    private String shopid;





}
