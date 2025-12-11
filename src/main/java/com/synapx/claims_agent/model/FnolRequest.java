package com.synapx.claims_agent.model;

public class FnolRequest {

    private String policyNumber;
    private String policyholderName;
    private String policyEffectiveFrom;
    private String policyEffectiveTo;

    private String incidentDate;
    private String incidentTime;
    private String incidentLocation;
    private String incidentDescription;

    private String claimantName;
    private String thirdPartyNames;
    private String contactDetails;

    private String assetType;
    private String assetId;
    private Double estimatedDamage;

    private String claimType;
    private String attachments;
    private Double initialEstimate;

    public FnolRequest() {
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyholderName() {
        return policyholderName;
    }

    public void setPolicyholderName(String policyholderName) {
        this.policyholderName = policyholderName;
    }

    public String getPolicyEffectiveFrom() {
        return policyEffectiveFrom;
    }

    public void setPolicyEffectiveFrom(String policyEffectiveFrom) {
        this.policyEffectiveFrom = policyEffectiveFrom;
    }

    public String getPolicyEffectiveTo() {
        return policyEffectiveTo;
    }

    public void setPolicyEffectiveTo(String policyEffectiveTo) {
        this.policyEffectiveTo = policyEffectiveTo;
    }

    public String getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(String incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getIncidentLocation() {
        return incidentLocation;
    }

    public void setIncidentLocation(String incidentLocation) {
        this.incidentLocation = incidentLocation;
    }

    public String getIncidentDescription() {
        return incidentDescription;
    }

    public void setIncidentDescription(String incidentDescription) {
        this.incidentDescription = incidentDescription;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getThirdPartyNames() {
        return thirdPartyNames;
    }

    public void setThirdPartyNames(String thirdPartyNames) {
        this.thirdPartyNames = thirdPartyNames;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Double getEstimatedDamage() {
        return estimatedDamage;
    }

    public void setEstimatedDamage(Double estimatedDamage) {
        this.estimatedDamage = estimatedDamage;
    }

    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }

    public Double getInitialEstimate() {
        return initialEstimate;
    }

    public void setInitialEstimate(Double initialEstimate) {
        this.initialEstimate = initialEstimate;
    }
}
