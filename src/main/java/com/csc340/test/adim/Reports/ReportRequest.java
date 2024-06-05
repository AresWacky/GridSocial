package com.csc340.test.adim.Reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {

    private String reportedBy;
    private String targetType;
    private Long targetId;
    private String reason;

    // Getters and setters

}


