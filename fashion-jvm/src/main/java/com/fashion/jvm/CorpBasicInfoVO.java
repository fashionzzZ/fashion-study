package com.fashion.jvm;

public class CorpBasicInfoVO {

    private String unitNumberId;

    private String companyName;

    private String certType;

    private String certCode;

    private String organizationLicense;

    public String getUnitNumberId() {
        return unitNumberId;
    }

    public void setUnitNumberId(String unitNumberId) {
        this.unitNumberId = unitNumberId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getOrganizationLicense() {
        return organizationLicense;
    }

    public void setOrganizationLicense(String organizationLicense) {
        this.organizationLicense = organizationLicense;
    }

    @Override
    public String toString() {
        return "CorpBasicInfoVO{" +
                "unitNumberId='" + unitNumberId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", certType='" + certType + '\'' +
                ", certCode='" + certCode + '\'' +
                ", organizationLicense='" + organizationLicense + '\'' +
                '}';
    }
}
