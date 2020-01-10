package com.hw.cy.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class eleaddress {
  private   BigDecimal longitude ;
  private   BigDecimal latitude;
    private  String address;
    private String qy;
        }
