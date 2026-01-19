package com.automation.ai.analyzer;

import com.automation.ai.model.GitChange;

import java.util.List;

public class ChangeClassifier {

    public boolean hasFunctionalChange(List<GitChange> changes) {
        return changes.stream().anyMatch(this::isFunctional);
    }

    private boolean isFunctional(GitChange change) {
        String file = change.getFilePath().toLowerCase();

        return file.endsWith(".java")
                || file.endsWith(".sql")
                || file.endsWith(".yml")
                || file.endsWith(".yaml")
                || file.endsWith(".json")
                || file.endsWith(".properties");
    }
}
