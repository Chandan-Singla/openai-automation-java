package com.automation.ai;

import com.automation.ai.git.GitDiffService;
import com.automation.ai.model.GitChange;
import com.automation.ai.analyzer.ChangeClassifier;
import com.automation.ai.analyzer.ImpactAnalyzer;

import java.util.List;

public class App {

    public static void main(String[] args) {

        // Single service instance
        GitDiffService gitDiffService = new GitDiffService();

        // Compare branches (for now)
        List<GitChange> changes =
                gitDiffService.getDiffBetweenBranches("feature-branch", "main");

        // Functional change detection
        ChangeClassifier classifier = new ChangeClassifier();
        boolean functional = classifier.hasFunctionalChange(changes);

        // Impact analysis
        ImpactAnalyzer impactAnalyzer = new ImpactAnalyzer();
        var impactedModules = impactAnalyzer.findImpactedModules(changes);

        System.out.println("Impacted modules:");
        impactedModules.forEach(m -> System.out.println("- " + m));

        System.out.println("Functional change detected: " + functional);
    }
}
