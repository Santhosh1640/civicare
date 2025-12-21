package com.civicare.assignment_service.kafka;

import com.civicare.assignment_service.entity.Assignment;
import com.civicare.assignment_service.event.ReportCreatedEvent;
import com.civicare.assignment_service.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReportCreatedConsumer {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @KafkaListener(topics = "report-created", groupId = "assignment-service")
    public void consume(ReportCreatedEvent event) {

        System.out.println("📥 Received event: " + event.getReportId());

        Assignment assignment = new Assignment();
        assignment.setReportId(event.getReportId());
        assignment.setIssueType(event.getIssueType());
        assignment.setAssignedTo("WARD-1");
        assignment.setAssignedAt(LocalDateTime.now());

        assignmentRepository.save(assignment);

        System.out.println("✅ Assignment saved");
    }
}
