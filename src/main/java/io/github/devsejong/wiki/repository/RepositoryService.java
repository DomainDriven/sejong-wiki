package io.github.devsejong.wiki.repository;

import java.nio.file.Path;

public interface RepositoryService {
    boolean isWorkingDirectoryExist();
    void cloneRepository();
    void update();

    Path getFilePath(String path);
}
