package com.mobiusvision.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InfoShareholder {
    private String shareholder;

    private String contributionsPledged;

    private String actualContribution;

    private String unifiedSocialCreditCode;

    private String useless;

    private String type;


}