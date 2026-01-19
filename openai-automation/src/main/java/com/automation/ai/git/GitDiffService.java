package com.automation.ai.git;

import com.automation.ai.model.GitChange;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GitDiffService {
//diffrence between two commits
    public List<GitChange> getDiffBetweenCommits(String oldCommit, String newCommit) {
        List<GitChange> changes = new ArrayList<>();

        try {
            Process process = new ProcessBuilder(
                    "git", "diff", "--name-status", oldCommit, newCommit
            ).start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    String status = parts[0];
                    String filePath = parts[1];

                    String changeType = mapStatus(status);
                    changes.add(new GitChange(filePath, changeType));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to get git diff", e);
        }

        return changes;
    }
// tofinddiffrence between branches
    public List<GitChange> getDiffBetweenBranches(String sourceBranch, String targetBranch) {
    List<GitChange> changes = new ArrayList<>();

    try {
        Process process = new ProcessBuilder(
                "git", "diff", "--name-status", targetBranch + ".." + sourceBranch
        ).start();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
        );

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\s+");
            if (parts.length >= 2) {
                String status = parts[0];
                String filePath = parts[1];

                changes.add(new GitChange(filePath, mapStatus(status)));
            }
        }

    } catch (Exception e) {
        throw new RuntimeException("Failed to compare branches", e);
    }

    return changes;
}


    private String mapStatus(String status) {
        switch (status) {
            case "A":
                return "ADDED";
            case "M":
                return "MODIFIED";
            case "D":
                return "DELETED";
            default:
                return "UNKNOWN";
        }
    }
}
