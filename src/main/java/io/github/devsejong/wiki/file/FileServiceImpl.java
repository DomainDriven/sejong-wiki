package io.github.devsejong.wiki.file;

import io.github.devsejong.wiki.file.git.GitService;
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
public class FileServiceImpl implements FileService {

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
            throw new FileServiceException("Failed to read data", e);
        }
    }

    @Override
    public void make(String path, String body) {
        throw new FileServiceException("작업 중!!");
    }

    @Override
    public void modify(String path, String body, String history) {
        throw new FileServiceException("작업 중!!");
    }


    @Override
    public void delete(String path) {
        throw new FileServiceException("작업 중!!");
    }

    Path getFilePath(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;

        return Paths.get(workingDirectory + path);
    }

}
