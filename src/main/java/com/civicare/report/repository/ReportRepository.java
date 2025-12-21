package com.civicare.report.repository;

import com.civicare.report.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("""
            SELECT r FROM Report r
            WHERE r.issueType = :issueType
                AND r.geoHash = :geoHash
                AND r.createdAt >= :since
            """)
    Optional<Report> findRecentDuplicate(
            String issueType,
            String geoHash,
            LocalDateTime since
    );
}
