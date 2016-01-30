package io.github.devsejong.wiki.docfile;

import io.github.devsejong.wiki.docfile.git.GitService;
import org.eclipse.jgit.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.devsejong.wiki.docfile.FileType.DIRECTORY;
import static io.github.devsejong.wiki.docfile.FileType.FILE;

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


    //FIXME 이름이 마음에 들지 않아...
    @Override
    public List<DirectoryContent> getDirContents(String dirPath) {
        try {
            Stream<Path> list = Files.list(getFilePath(dirPath));
            return list.map(path -> getDirectoryContent(dirPath, path)).collect(Collectors.toList());
        } catch (IOException e) {
            throw new DocFileServiceException("Cannot found folder.", e);
        }
    }

    private DirectoryContent getDirectoryContent(String dirPath, Path path) {
        File file = path.toFile();
        String fileName = file.getName();
        FileType type = file.isDirectory() ? DIRECTORY : FILE;
        boolean hasChildren = false;
        if (type == DIRECTORY) {
            Path childDirPath = getFilePath(dirPath + "/" + fileName);
            if (childDirPath.toFile().list().length > 0)
                hasChildren = true;
        }

        DirectoryContent content = new DirectoryContent();
        content.setId(dirPath + "/" + file.getName());
        content.setText(file.getName());
        content.setHasChildren(hasChildren);
        content.setType(type);

        return content;
    }


    Path getFilePath(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;

        return Paths.get(workingDirectory + path);
    }

}
