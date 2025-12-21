package com.civicare.report.service;

import com.civicare.report.dto.CreateReportRequest;
import com.civicare.report.event.ReportCreatedEvent;
import com.civicare.report.kafka.ReportEventProducer;
import com.civicare.report.model.IdempotencyKey;
import com.civicare.report.model.Report;
import com.civicare.report.repository.IdempotencyRepository;
import com.civicare.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService {

    @Autowired
    public ReportRepository reportRepository;

    @Autowired
    public IdempotencyRepository idempotencyRepository;

    @Autowired
    public ReportEventProducer reportEventProducer;

    public Long createReport(CreateReportRequest request, String idempotencyKey) {
        // Implementation for creating a report
        return idempotencyRepository.findById(idempotencyKey)
                .map(IdempotencyKey::getReportId)
                .orElseGet(() -> {

                    String geoHash = computeGeoHash(request.getLatitude(), request.getLongitude());
                    LocalDateTime since = LocalDateTime.now().minusHours(24);

                    Optional<Report> duplicate =
                            reportRepository.findRecentDuplicate(
                                    request.getIssueType(),
                                    geoHash,
                                    since);

                    if(duplicate.isPresent()) {
                        Report existing = duplicate.get();
                        existing.setDuplicateCount(existing.getDuplicateCount() + 1);
                        reportRepository.save(existing);
                        return existing.getId();
                    }

                    // no duplicate -> create new report
                    Report report = new Report();
                    report.setIssueType(request.getIssueType());
                    report.setLatitude(request.getLatitude());
                    report.setLongitude(request.getLongitude());
                    report.setGeoHash(geoHash);

                    Report saved = reportRepository.save(report);

                    System.out.println("PUBLISHING KAFKA EVENT FOR REPORT " + saved.getId());

                    reportEventProducer.publishReportCreated(
                            new ReportCreatedEvent(
                                    saved.getId(),
                                    saved.getIssueType(),
                                    saved.getGeoHash(),
                                    saved.getCreatedAt()
                            )
                    );

                    IdempotencyKey key = new IdempotencyKey();
                    key.setIdempotencyKey(idempotencyKey);
                    key.setReportId(saved.getId());
                    idempotencyRepository.save(key);



                    return saved.getId();
                });
    }

    private String computeGeoHash(Double lat, Double lon) {
        return Math.round(lat * 1000) + ":" + Math.round(lon * 1000);
    }
}
