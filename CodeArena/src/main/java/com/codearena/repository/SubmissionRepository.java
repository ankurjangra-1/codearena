package com.codearena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codearena.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}