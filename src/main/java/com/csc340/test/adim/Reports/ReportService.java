package com.csc340.test.adim.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report createReport(String reportedBy, String targetType, Long targetId, String reason) {
        Report report = new Report();
        report.setReportedBy(reportedBy);
        report.setTargetType(targetType);
        report.setTargetId(targetId);
        report.setReason(reason);
        report.setReportedAt(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }


}

