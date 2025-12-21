package com.civicare.report.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String issueType;

    // Address
    private Double latitude;
    private Double longitude;

    private String status;

    private LocalDateTime createdAt;

    private String geoHash;

    private Integer duplicateCount;

    @PrePersist
    public void prePersist() {
        this.status = "OPEN";
        this.createdAt = LocalDateTime.now();
        if (this.duplicateCount == null) {
            this.duplicateCount = 1;
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(Integer duplicateCount) {
        this.duplicateCount = duplicateCount;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }
}
