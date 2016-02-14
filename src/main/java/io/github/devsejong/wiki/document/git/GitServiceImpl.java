package io.github.devsejong.wiki.document.git;

import io.github.devsejong.wiki.document.DocFileServiceException;
import io.github.devsejong.wiki.document.util.DocFilePathHolder;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Profile("!unitTest")
@Slf4j
public class GitServiceImpl implements GitService {

    @Value("${wiki.gitUrl}")
    String gitUrl;

    @Autowired
    DocFilePathHolder docFilePathHolder;

    @PostConstruct
    private void initWiki() {
        if (isWorkingDirectoryExist())
            update();
        else
            cloneRepository();
    }

    private boolean isWorkingDirectoryExist() {
        //TODO 해당 폴더가 읽기 및 쓰기가 가능한지, git레포지토리인지를 확인하는 로직 필요.
        return Files.exists(
                Paths.get(docFilePathHolder.getWorkingDirectory())
        );
    }

    //깃헙과 연동되는 부분은 추후 작업.
    @Scheduled(fixedDelay = 1000 * 60)
    public void update() {
        try {
            PullResult call = Git.open(
                    new File(docFilePathHolder.getWorkingDirectory())
            ).pull().call();

            if (call.isSuccessful())
                log.debug("git저장소와 동기화 성공");
            else
                log.error("실패");

        } catch (GitAPIException | IOException e) {
            throw new DocFileServiceException("Failed to update repository", e);
        }
    }

    //깃헙과 연동되는 부분은 추후 작업 예정
    public void cloneRepository() {
        try {
            Git.cloneRepository().setURI(gitUrl).setBranch("master").setDirectory(new File(docFilePathHolder.getWorkingDirectory())).call();
        } catch (GitAPIException e) {
            throw new DocFileServiceException("Failed to clone repository", e);
        }
    }

}
