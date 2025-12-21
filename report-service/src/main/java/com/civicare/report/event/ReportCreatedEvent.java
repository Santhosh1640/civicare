package com.civicare.report.event;

import java.time.LocalDateTime;

public class ReportCreatedEvent {
    private Long reportId;
    private String issueType;
    private String geoHash;
    private LocalDateTime createdAt;

    public ReportCreatedEvent(Long reportId, String issueType, String geoHash, LocalDateTime createdAt) {
        this.reportId = reportId;
        this.issueType = issueType;
        this.geoHash = geoHash;
        this.createdAt = createdAt;
    }

    public Long getReportId() {
        return reportId;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
