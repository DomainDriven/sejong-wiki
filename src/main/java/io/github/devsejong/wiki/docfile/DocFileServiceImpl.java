package io.github.devsejong.wiki.docfile;

import io.github.devsejong.wiki.docfile.git.GitService;
import org.eclipse.jgit.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DocFileServiceImpl implements DocFileService {

    @Value("${wiki.workingDirectory}")
    String workingDirectory;

    @Autowired
    GitService gitService;

    @Override
    public String read(String path) {
        Path filePath = this.getFilePath(path);
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            return StringUtils.join(lines, "\n");
        } catch (IOException e) {
            throw new DocFileServiceException("Failed to read data", e);
        }
    }

    @Override
    public void make(String path, String body) {
        throw new DocFileServiceException("작업 중!!");
    }

    @Override
    public void modify(String path, String body, String history) {
        throw new DocFileServiceException("작업 중!!");
    }


    @Override
    public void delete(String path) {
        throw new DocFileServiceException("작업 중!!");
    }

    Path getFilePath(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;

        return Paths.get(workingDirectory + path);
    }

}
