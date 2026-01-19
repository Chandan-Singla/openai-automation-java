package com.automation.ai.config;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     AppConfig.class
                             .getClassLoader()
                             .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("application.properties not found");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }
// to get git branch or commit details
    public static String get(String key) {
        return properties.getProperty(key);
    }
    public static String getGitRepoPath() {
    return properties.getProperty("git.repo.path");
}

public static String getBaseRef() {
    return properties.getProperty("git.base.ref");
}

public static String getTargetRef() {
    return properties.getProperty("git.target.ref");
}

}
