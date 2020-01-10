package com.mobiusvision.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InfoPropertyRightPatent {
    private String name;

    private String type;

    private String applicationNumber;

    private String filingDate;

    private String applicationPublicationNumber;

    private String applicationPublicationDate;

    private String legalHistory;

    private String inventor;

    private String professionalApplicants;

    private String professionalGency;

    private String professionalAgent;

    private String legalStatus;

    private String details;

    private String useless;

    private String unifiedSocialCreditCode;


}