package io.github.devsejong.wiki.data.git;

import io.github.devsejong.wiki.data.DataException;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class GitService {

    @Value("${wiki.git.url}")
    private String gitUrl;
    @Value("${wiki.working.directory}")
    private String workingDirectory;


    @PostConstruct
    public void initWiki() {
        if (isWorkingDirectoryExist())
            update();
        else
            cloneRepository();
    }

    private boolean isWorkingDirectoryExist() {
        //TODO 해당 폴더가 읽기 및 쓰기가 가능한지, git레포지토리인지를 확인하는 로직 필요.
        return Files.exists(Paths.get(workingDirectory));
    }


    // 업데이트는 알아서 작동하여야 한다. 외부에는 공개되면 안된다.
    public void update() {
        try {
            Git.open(new File(workingDirectory)).pull().call();
        } catch (GitAPIException | IOException e) {
            throw new DataException("Failed to update repository", e);
        }
    }

    public void cloneRepository() {
        try {
            Git.cloneRepository().setURI(gitUrl).setBranch("master").setDirectory(new File(workingDirectory)).call();
        } catch (GitAPIException e) {
            throw new DataException("Failed to clone repository", e);
        }
    }


}
