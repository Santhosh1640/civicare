package com.civicare.report.kafka;

import com.civicare.report.event.ReportCreatedEvent;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReportEventProducer {
    private static final String TOPIC = "report-created";

    private final KafkaTemplate<String, ReportCreatedEvent> kafkaTemplate;

    public ReportEventProducer(KafkaTemplate<String, ReportCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishReportCreated(ReportCreatedEvent event) {
        kafkaTemplate.send(
                TOPIC,
                event.getReportId().toString(),
                event
        );
    }


}
