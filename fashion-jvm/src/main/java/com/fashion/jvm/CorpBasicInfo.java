package com.fashion.jvm;

public class CorpBasicInfo {

    private String unitNumberId;

    private String businessLicense;

    private String organizationLicense;

    private String taxRegistrationCertificate;

    public String getUnitNumberId() {
        return unitNumberId;
    }

    public void setUnitNumberId(String unitNumberId) {
        this.unitNumberId = unitNumberId;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getOrganizationLicense() {
        return organizationLicense;
    }

    public void setOrganizationLicense(String organizationLicense) {
        this.organizationLicense = organizationLicense;
    }

    public String getTaxRegistrationCertificate() {
        return taxRegistrationCertificate;
    }

    public void setTaxRegistrationCertificate(String taxRegistrationCertificate) {
        this.taxRegistrationCertificate = taxRegistrationCertificate;
    }

    @Override
    public String toString() {
        return "CorpBasicInfo{" +
                "unitNumberId='" + unitNumberId + '\'' +
                ", businessLicense='" + businessLicense + '\'' +
                ", organizationLicense='" + organizationLicense + '\'' +
                ", taxRegistrationCertificate='" + taxRegistrationCertificate + '\'' +
                '}';
    }
}
