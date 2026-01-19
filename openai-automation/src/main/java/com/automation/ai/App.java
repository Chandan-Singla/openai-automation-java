package com.automation.ai;

import com.automation.ai.git.GitDiffService;
import com.automation.ai.model.GitChange;
import com.automation.ai.analyzer.ChangeClassifier;
import com.automation.ai.analyzer.ImpactAnalyzer;

import java.util.List;
import com.automation.ai.git.DiffMode;

public class App {

    public static void main(String[] args) {

        GitDiffService gitDiffService = new GitDiffService();

        // ---- CONFIG (temporary hardcoded) ----
        DiffMode mode = DiffMode.BRANCH;
        String source = "case-a-functional";
        String target = "main";
        // --------------------------------------

        List<GitChange> changes;

        if (mode == DiffMode.BRANCH) {
            changes = gitDiffService.getDiffBetweenBranches(source, target);
        } else {
            changes = gitDiffService.getDiffBetweenCommits(source, target);
        }
      
        ChangeClassifier classifier = new ChangeClassifier();
        boolean functional = classifier.hasFunctionalChange(changes);

        ImpactAnalyzer impactAnalyzer = new ImpactAnalyzer();
        var impactedModules = impactAnalyzer.findImpactedModules(changes);

        // System.out.println("Impacted modules:");
        // impactedModules.forEach(m -> System.out.println("- " + m));

        // System.out.println("Functional change detected: " + functional);

        System.out.println("==== CHANGE ANALYSIS RESULT ====");

if (functional) {
    System.out.println("CASE A: Functional change detected");
} else {
    System.out.println("CASE B: Non-functional change only");
}

System.out.println("\nImpacted modules:");
impactedModules.forEach(m -> System.out.println("- " + m));
    }
}
