package com.hw.cy.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class cy_inside_channels {
    //渠道
    private String channel;
    //日期
    private String inputdate;
    //销量
    private  Integer salesvolume;
    //店铺id
    private  String shopid;
}
