package io.github.devsejong.wiki.repository;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    @Value("${wiki.git.url}")
    private String gitUrl;
    @Value("${wiki.working.directory}")
    private String workingDirectory;

    @Override
    public boolean isWorkingDirectoryExist() {
        //TODO 해당 폴더가 읽기 및 쓰기가 가능한지, git레포지토리인지를 확인하는 로직 필요.
        return Files.exists(Paths.get(workingDirectory));
    }

    @Override
    public void cloneRepository() {
        try {
            Git.cloneRepository().setURI(gitUrl).setBranch("master").setDirectory(new File("repo")).call();
        } catch (GitAPIException e) {
            throw new RepsitoryException("Failed to clone repository", e);
        }
    }

    @Override
    public void update() {
        try {
            Git.open(new File(workingDirectory)).pull().call();
        } catch (GitAPIException | IOException e) {
            throw new RepsitoryException("Failed to update repository", e);
        }
    }

    @Override
    public Path getFilePath(String path){
        return Paths.get(workingDirectory, path);
    }

}
