package com.automation.ai;

import com.automation.ai.git.GitDiffService;

public class App {

    public static void main(String[] args) {

        System.out.println("Comparing branches...");

        String diff = GitDiffService.getDiff("main", "feature");

        if (diff.isBlank()) {
            System.out.println("No changes detected.");
        } else {
            System.out.println("Changes found:\n");
            System.out.println(diff);
        }
    }
}
