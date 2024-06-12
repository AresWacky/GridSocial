package com.gridsocial.controller;


import com.gridsocial.model.User;
import com.gridsocial.model.ReportRequest;
import com.gridsocial.service.ReportService;
import com.gridsocial.service.UserService;
import com.gridsocial.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping()
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

    @PostMapping("/report/create")
    public Report createReport(@RequestBody ReportRequest reportRequest) {
        return reportService.createReport(
                reportRequest.getReportedBy(),
                reportRequest.getTargetType(),
                reportRequest.getTargetId(),
                reportRequest.getReason()
        );

    }


    @GetMapping("/report/all")
    public String getAllUsers(Model model,
                              @RequestParam(name = "continue", required = false) String cont) {
        model.addAttribute("reportList", reportService.getAllReports());
        return "/admin/Report";
    }

    @PostMapping("/report/action/{id}")
    public String updateReportAction(@PathVariable Long id, @RequestParam String action) {
        Report report = reportService.getReportById(id);

        if ("BAN".equalsIgnoreCase(action) && "PERSON".equalsIgnoreCase(report.getTargetType())) {
            userService.banUser(report.getTargetId());
        }

        if (!"IGNORE".equalsIgnoreCase(action)) {
            reportService.updateReportAction(id, action);
        }

        reportService.deleteReport(id);

        return "redirect:/admin/report/all"; // Redirect to the report list after updating action
    }

    @GetMapping("/filter")
    public String getReportsByTargetType(Model model,
                                         @RequestParam(name = "targetType", required = false) String targetType) {
        List<Report> reports;
        if (targetType == null || targetType.isEmpty()) {
            reports = reportService.getAllReports();
            targetType = ""; // Ensure the "All" option is selected
        } else {
            reports = reportService.getReportsByTargetType(targetType);
        }
        model.addAttribute("reportList", reports);
        model.addAttribute("selectedFilter", targetType);
        return "/admin/Report";
    }
}