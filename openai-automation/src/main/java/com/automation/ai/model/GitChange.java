package com.automation.ai.model;

public class GitChange {

    private String filePath;
    private String changeType; // ADDED, MODIFIED, DELETED

    public GitChange(String filePath, String changeType) {
        this.filePath = filePath;
        this.changeType = changeType;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getChangeType() {
        return changeType;
    }

    @Override
    public String toString() {
        return changeType + " -> " + filePath;
    }
}
