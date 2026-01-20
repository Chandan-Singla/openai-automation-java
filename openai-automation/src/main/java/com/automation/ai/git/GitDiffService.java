package com.automation.ai.git;

import com.automation.ai.model.GitChange;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GitDiffService {

    private final Repository repository;

    public GitDiffService() {
        try {
            this.repository = new FileRepositoryBuilder()
                    .setGitDir(new File(".git"))
                    .readEnvironment()
                    .findGitDir()
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to open git repository", e);
        }
    }
    // -------------------------------------------------------------------------------------------------------
    public List<GitChange> getDiffBetweenCommits(String oldCommit, String newCommit) {
    try (Git git = new Git(repository);
         ObjectReader reader = repository.newObjectReader()) {

        ObjectId oldHead = repository.resolve(oldCommit);
        ObjectId newHead = repository.resolve(newCommit);

        CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
        oldTreeIter.reset(reader, repository.parseCommit(oldHead).getTree());

        CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
        newTreeIter.reset(reader, repository.parseCommit(newHead).getTree());

        List<DiffEntry> diffs = git.diff()
                .setOldTree(oldTreeIter)
                .setNewTree(newTreeIter)
                .call();

        return convertToGitChanges(diffs);

    } catch (Exception e) {
        throw new RuntimeException("Failed to diff commits", e);
    }
}

// -------------------------------------------------------------------------------------------------------------------------------

    public List<GitChange> getDiffBetweenBranches(String baseBranch, String targetBranch) {
    try (Git git = new Git(repository)) {

        ObjectId base = repository.resolve("refs/heads/" + baseBranch);
        ObjectId target = repository.resolve("refs/heads/" + targetBranch);

        if (base == null || target == null) {
            throw new RuntimeException(
                "Branch not found. base=" + baseBranch + ", target=" + targetBranch
            );
        }

        List<DiffEntry> diffs = git.diff()
                .setOldTree(Utils.prepareTreeParser(repository, base))
                .setNewTree(Utils.prepareTreeParser(repository, target))
                .call();

        return convertToGitChanges(diffs);

    } catch (Exception e) {
        throw new RuntimeException("Error while diffing branches", e);
    }
}


    private List<GitChange> convertToGitChanges(List<DiffEntry> diffs) throws Exception {
    List<GitChange> changes = new ArrayList<>();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
try (DiffFormatter formatter = new DiffFormatter(out)) {
    formatter.setRepository(repository);

    for (DiffEntry diff : diffs) {
        formatter.format(diff);

        String diffText = out.toString();
        out.reset();

        changes.add(new GitChange(
                diff.getNewPath(),
                diff.getChangeType().name(),
                diffText
        ));
    }
}


    return changes;
}

}
