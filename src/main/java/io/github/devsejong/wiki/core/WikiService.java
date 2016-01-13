package io.github.devsejong.wiki.core;

import io.github.devsejong.wiki.repository.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WikiService {

    @Autowired
    private RepositoryService repoService;

    public void initWiki() {
        if (repoService.isWorkingDirectoryExist()) {
            repoService.update();
        } else {
            repoService.cloneRepository();
        }
    }
}
