package com.automation.ai.model;

public class GitChange {

    private String filePath;
    private String changeType; // ADDED, MODIFIED, DELETED
    private String diff;


    public GitChange(String filePath, String changeType, String diff) {
        this.filePath = filePath;
        this.changeType = changeType;
        this.diff = diff;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getChangeType() {
        return changeType;
    }
    public String getDiff() {
        return diff;
    }

    // @Override
    // public String toString() {
    //     return changeType + " -> " + filePath;
    // }
}
