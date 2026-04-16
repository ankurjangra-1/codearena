package com.codearena.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codearena.dto.SubmissionResponse;
import com.codearena.entity.Submission;
import com.codearena.entity.User;
import com.codearena.service.SubmissionService;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/{problemId}")
    public SubmissionResponse submit(
            @PathVariable Long problemId,
            @RequestParam String code,
            @RequestParam String language,
            Authentication authentication
    ) {

        User user = (User) authentication.getPrincipal();
        String email = user.getEmail();

        Submission submission = submissionService.submitCode(problemId, email, code, language);
        SubmissionResponse response = new SubmissionResponse();
        response.setCode(submission.getCode());
        response.setLanguage(submission.getLanguage());
        response.setStatus(submission.getStatus());
        response.setUserEmail(submission.getUser().getEmail());
        response.setProblemTitle(submission.getProblem().getTitle());

        return response;
    }
}