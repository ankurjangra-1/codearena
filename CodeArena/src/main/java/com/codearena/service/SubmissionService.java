package com.codearena.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.codearena.entity.*;
import com.codearena.repository.*;

@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final ProblemRepository problemRepository;
    private final UserRepository userRepository;

    public SubmissionService(SubmissionRepository submissionRepository,
                             ProblemRepository problemRepository,
                             UserRepository userRepository) {
        this.submissionRepository = submissionRepository;
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    public Submission submitCode(Long problemId, String email, String code, String language) {

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔥 fake judge system
        String status = new Random().nextBoolean() ? "ACCEPTED" : "REJECTED";

        Submission submission = new Submission();
        submission.setCode(code);
        submission.setLanguage(language);
        submission.setStatus(status);
        submission.setProblem(problem);
        submission.setUser(user);

        return submissionRepository.save(submission);
    }
}