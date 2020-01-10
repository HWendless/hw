package com.hw.cy.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class cy_inside_orderstatistics {
    //
    //日期
    private String inputdate;
    //销量
    private  Integer salesvolume;
    //新品销量
    private  Integer  newsalesvolume;
    //店铺id
    private  String shopid;
}
