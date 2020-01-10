package com.sj_cy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class po {
//    省，市，区，门店名称，品牌（7，coco），分类，产品名称，价格，月销，热度
//    经度，维度
    //省
    private String province;
    //市
    private String city;
    //区
    private String district;
    //门店名称
    private String store_name;
    //品牌
    private String brand_name;
    //分类
    private String classification;
    //产品名称
    private String product_name;
    //价格
    private String price;
    //商铺月销
    private String monthly_sales;
    //商品月销
    private String food_monthly_sales;
    //热度
    private String degree_of_heat;
    //经度
    private String longitude;
    //维度
    private String latitude;

}
