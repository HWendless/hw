package com.hw.cy.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ele_Shop {
    //店铺地址
    private String address;
    //店铺的名字
    private String name;
    //店铺的纬度
    private String lat;
    //店铺的经度
    private String lon;
    //店铺的电话
    private String phone;
    //店铺id
    private String id;
    //品牌的id
    private String brandid;
    //店铺的月销量
    private Integer recentordernum;
    //店铺的评分
    private double shoprating;
    //店铺所属的区域
    private String area="";
    //插入时间
    private String insertdata;
    //评论总数
    private Integer commentscount;
    //好评
    private Integer good;
    //差评
    private Integer bad;
    //有图
    private Integer picture;
    //味道好
    private Integer tastesgood;
    //人均
    private Integer percapita;


}
