package com.hw.cy.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class eleshop_food {
    //饿了么店铺id
    private String eleShopId;
    //饿了么店铺名称
    private String eleShopName;
    //纬度
    private String eleLat;
    //经度
    private String eleLon;
    //店铺地址
    private String eleShopAddr;
    //店铺电话
    private String eleShopPhone;
    //店铺评价
    private Double rating;
    //评价总数
    private Integer ratingCount;
    //最近订单数
    private Integer recentOrderNum;
    //单品系列
    private String foodType;
    //单品名称
    private String foodName;
    //原料
    private String materials;
    //单品评分
    private String foodRating;
    //单品最低价
    private String lowestPrice;
    //月销量
    private Integer monthSales;
    //单品评分总数
    private Integer foodRatingCount;
    //单品满意数
    private Integer satisfyCount;
    //单品满意率
    private Integer satisfyRate;
    //创建时间
    private Date createTime;

}
