package com.mobiusvision.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InfoPropertyRightWebsite {
    private String homeAddress;

    private String websiteAme;

    private String domainName;

    private String recordNo;

    private String unifiedSocialCreditCode;

    private String useless;

}