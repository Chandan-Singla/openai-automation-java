package com.automation.ai.analyzer;

import com.automation.ai.model.GitChange;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImpactAnalyzer {

    public Set<String> findImpactedModules(List<GitChange> changes) {
        Set<String> impacted = new HashSet<>();

        for (GitChange change : changes) {
            String path = change.getFilePath();

            if (path.contains("src/main/java")) {
                String[] parts = path.split("/");

                // src/main/java/com/automation/ai/<module>/*
                if (parts.length > 6) {
                    impacted.add(parts[6]); // module name
                }
            }
        }
        return impacted;
    }
}
