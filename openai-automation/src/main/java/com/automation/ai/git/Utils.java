package com.automation.ai.git;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

public class Utils {

    public static CanonicalTreeParser prepareTreeParser(
            Repository repository, ObjectId objectId) throws Exception {

        try (RevWalk walk = new RevWalk(repository)) {
            var commit = walk.parseCommit(objectId);
            ObjectId treeId = commit.getTree().getId();

            CanonicalTreeParser treeParser = new CanonicalTreeParser();
            try (ObjectReader reader = repository.newObjectReader()) {
                treeParser.reset(reader, treeId);
            }
            walk.dispose();
            return treeParser;
        }
    }
}
