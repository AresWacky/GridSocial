package com.csc340.test.adim.Reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public Report createReport(@RequestBody ReportRequest reportRequest) {
        return reportService.createReport(
                reportRequest.getReportedBy(),
                reportRequest.getTargetType(),
                reportRequest.getTargetId(),
                reportRequest.getReason()
        );
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }
}

