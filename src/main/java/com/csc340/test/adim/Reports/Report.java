package com.csc340.test.adim.Reports;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportedBy;

    private String targetType; // Can be "PERSON", "GROUP", or "COMMENT"

    private Long targetId;

    private String reason;

    private LocalDateTime reportedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setReportedAt(LocalDateTime reportedAt) {
        this.reportedAt = reportedAt;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getReportedAt() {
        return reportedAt;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public Long getTargetId() {
        return targetId;
    }

    public String getTargetType() {
        return targetType;
    }
}

