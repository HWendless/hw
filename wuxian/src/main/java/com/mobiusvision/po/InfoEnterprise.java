package com.mobiusvision.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class InfoEnterprise {
    private Float registeredCapital;

    private String legalRepresentative;

    private String nameUsedBefore;

    private String unifiedSocialCreditCode;

    private String companyNumber;

    private String registrationAuthority;

    private String enterpriseType;

    private String administrativeDivision;

    private String registrationAddress;

    private String scopeofBusiness;

    private String paidCapital;

    private String businessStatus;

    private String industry;

    private String taxpayerIdentificationNumber;

    private String organizationalCode;

    private String dateOfEstablishment;

    private String businessTerm;

    private String annualInspectionDate;

    private String useless;

    private String mailbox;

    private String telephone;

    private String describe;

    private String name;


}