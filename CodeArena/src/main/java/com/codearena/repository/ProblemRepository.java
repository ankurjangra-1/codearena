package com.codearena.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codearena.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
	Page<Problem> findByDifficulty(String difficulty, Pageable pageable);
	Page<Problem> findByTitleContaining(String keyword, Pageable pageable);
}