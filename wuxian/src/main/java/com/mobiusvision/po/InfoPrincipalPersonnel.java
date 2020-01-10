package com.mobiusvision.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InfoPrincipalPersonnel {
    private String name;

    private String post;

    private String unifiedSocialCreditCode;

    private String useless;


}