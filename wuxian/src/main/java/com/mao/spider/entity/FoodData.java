package com.mao.spider.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FoodData implements Serializable {

    //大人点评店铺Id
    private String dzShopId;

    //大众点评店铺名称
    private String dzShopName;

    //大众点评店铺url
    private String dzShopUrl;

    //大众点评店铺地址
    private String dzShopAddr;

    //大众点评电话
    private String dzTel;

    private String boughtList;

    private String menu;

    private String recomment;

    private String redpack;

    private String rst;









}
