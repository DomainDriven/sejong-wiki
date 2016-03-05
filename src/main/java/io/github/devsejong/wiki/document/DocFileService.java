package io.github.devsejong.wiki.document;

import io.github.devsejong.wiki.document.DocFileServiceException.DirectoryNotFoundException;
import io.github.devsejong.wiki.document.DocFileServiceException.DocumentNotFoundException;
import io.github.devsejong.wiki.document.DocFileServiceException.FailToReadFileException;
import io.github.devsejong.wiki.document.git.GitService;
import io.github.devsejong.wiki.document.util.DocFilePathHolder;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static io.github.devsejong.wiki.document.FileType.DIRECTORY;
import static io.github.devsejong.wiki.document.FileType.FILE;
import static java.util.stream.Collectors.toList;

@Service
public class DocFileService {
    @Value("${wiki.ignoreFiles}")
    String ignoreFiles;
    @Autowired
    GitService gitService;

    @Autowired
    DocFilePathHolder docFilePathHolder;

    Set<String> ignoreFileSet;

    @PostConstruct
    public void init() {
        ignoreFileSet = new HashSet<>();
        Collections.addAll(ignoreFileSet, ignoreFiles.split("\\|"));
    }

    public Document read(String path) {
        Path filePath = this.getFilePath(path);

        if (Files.notExists(filePath) || Files.isDirectory(filePath))
            throw new DocumentNotFoundException();
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            String body = StringUtils.join(lines, "\n");
            return new Document(path, body);
        } catch (IOException e) {
            throw new FailToReadFileException(e);
        }
    }

    public void create(String path, String body) {
        throw new UnsupportedOperationException("작업 중!!");
    }

    public void modify(String path, String body, String history) {
        throw new UnsupportedOperationException("작업 중!!");
    }

    public void delete(String path) {
        throw new UnsupportedOperationException("작업 중!!");
    }

    //FIXME 이름이 마음에 들지 않아...
    public List<DirectoryContent> getDirContents(String dirPath) {

        Stream<Path> list;
        try {
            list = Files.list(getFilePath(dirPath));
            return list.filter(this::isNotIgnoreFile)
                    .map(p -> getDirectoryContent(dirPath, p))
                    .sorted(this::directoryContentsCompare)
                    .collect(toList());
        } catch (IOException e) {
            throw new DirectoryNotFoundException(e);
        }
    }

    private int directoryContentsCompare(DirectoryContent o1, DirectoryContent o2) {
        if (o1.isHasChildren() != o2.isHasChildren())
            return o1.isHasChildren() ? -1 : 1;
        else
            return o1.getLabel().compareTo(o2.getLabel());
    }

    private boolean isNotIgnoreFile(Path path) {
        String fileName = path.getFileName().toString();
        return !ignoreFileSet.contains(fileName);
    }

    //file을 DirectoryContent로 변경.
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
        content.setId(Paths.get(dirPath, file.getName()).toString());
        content.setLabel(file.getName());
        content.setHasChildren(hasChildren);

        return content;
    }

    Path getFilePath(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;

        return Paths.get(docFilePathHolder.getWorkingDirectory() + path);
    }

}
