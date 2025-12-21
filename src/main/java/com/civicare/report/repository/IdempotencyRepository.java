package com.civicare.report.repository;

import com.civicare.report.model.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdempotencyRepository extends JpaRepository<IdempotencyKey, String> {

}
