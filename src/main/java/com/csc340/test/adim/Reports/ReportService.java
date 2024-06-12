package com.csc340.test.adim.Reports;
import com.csc340.test.adim.User.User;
import com.csc340.test.adim.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserService userService;

    public Report createReport(String reportedBy, String targetType, Long targetId, String reason) {
        Report report = new Report();
        report.setReportedBy(reportedBy);
        report.setTargetType(targetType);
        report.setTargetId(targetId);
        report.setReason(reason);
        report.setDate(LocalDate.now());
        return reportRepository.save(report);
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public List<Report> getReportsByTargetType(String targetType) {
        return reportRepository.findByTargetType(targetType);
    }

    public Report getReportById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid report ID: " + id));
    }

    public void updateReportAction(Long id, String action) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid report ID: " + id));
        report.setAction(action);
        reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}