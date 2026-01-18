package com.automation.ai.git;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitDiffService {

    public static String getDiff(String baseBranch, String targetBranch) {
        StringBuilder diffOutput = new StringBuilder();

        try {
            ProcessBuilder builder = new ProcessBuilder(
                    "git", "diff", baseBranch + ".." + targetBranch
            );
            builder.redirectErrorStream(true);

            Process process = builder.start();

            try (BufferedReader reader =
                         new BufferedReader(new InputStreamReader(process.getInputStream()))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    diffOutput.append(line).append("\n");
                }
            }

            process.waitFor();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get git diff", e);
        }

        return diffOutput.toString();
    }
}
