package io.github.devsejong.wiki.data;

import io.github.devsejong.wiki.data.git.GitService;
import org.eclipse.jgit.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    @Value("${wiki.working.directory}")
    private String workingDirectory;

    @Autowired
    GitService gitService;


    @PostConstruct
    private void initWiki() {
        gitService.initWiki();
    }

    private Path getFilePath(String path){
        return Paths.get(workingDirectory, path);
    }

    @Override
    public void make(String path, String body) {
    }

    @Override
    public void modify(String path, String body, String history) {

    }

    @Override
    public void delete(String path) {

    }

    @Override
    public String read(String path){
        Path filePath = this.getFilePath(path);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            return StringUtils.join(lines, "\n");
        } catch (IOException e) {
            throw new DataException("Failed to read data", e);
        }
    }

    @Override
    public File readFile(String path){
        Path filePath = this.getFilePath(path);
        return filePath.toFile();
    }


}
