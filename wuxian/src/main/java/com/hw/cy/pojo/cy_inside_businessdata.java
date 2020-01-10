package com.hw.cy.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class cy_inside_businessdata {
    //日期
    private String inputdate;
    //销量
    private  Integer salesvolume;
    //收入
    private  Integer  income;
    //成本
    private  Integer cost;
    //对比
    private  String contrast;
    //店铺id
    private  String shopid;


}
