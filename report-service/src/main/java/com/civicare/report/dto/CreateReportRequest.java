package com.civicare.report.dto;

import jakarta.validation.constraints.NotNull;

public class CreateReportRequest {

    @NotNull
    private String issueType;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    // Getters and Setters
    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
