package com.qrcode;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class vo {


    private String ID;
    private String CUSTOMER_NAME;
    private String CUSTOMER_CODE;
    private  BigDecimal LAT;
    private BigDecimal LON;
    private Integer CUSTOMER_ORDER;
    private String IS_ACTIVE;
    private  String DELIVERYLINE_CODE;

}
