package com.codearena.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codearena.entity.Problem;
import com.codearena.repository.ProblemRepository;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }

    public Problem getProblemById(Long id) {
        return problemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
    }
    public Page<Problem> getProblems(Pageable pageable, String difficulty, String keyword) {

        if (keyword != null && !keyword.isEmpty()) {
            return problemRepository.findByTitleContaining(keyword, pageable);
        }

        if (difficulty != null && !difficulty.isEmpty()) {
            return problemRepository.findByDifficulty(difficulty, pageable);
        }

        return problemRepository.findAll(pageable);
    }
}