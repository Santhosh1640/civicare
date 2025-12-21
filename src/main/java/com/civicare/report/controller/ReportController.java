package com.civicare.report.controller;

import com.civicare.report.dto.CreateReportRequest;
import com.civicare.report.model.Report;
import com.civicare.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reports")
public class ReportController {

    @Autowired
    public ReportService reportService;

    @PostMapping
    public ResponseEntity<Long> createReport(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @RequestBody CreateReportRequest request) {

        Long reportId = reportService.createReport(request,idempotencyKey);
        return ResponseEntity.ok(reportId);

    }
}
