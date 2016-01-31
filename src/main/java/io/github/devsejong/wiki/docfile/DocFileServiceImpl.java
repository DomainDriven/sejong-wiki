package io.github.devsejong.wiki.docfile;

import io.github.devsejong.wiki.docfile.git.GitService;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.devsejong.wiki.docfile.FileType.DIRECTORY;
import static io.github.devsejong.wiki.docfile.FileType.FILE;

@Service
public class DocFileServiceImpl implements DocFileService {

    @Value("${wiki.workingDirectory}")
    String workingDirectory;
    @Value("${wiki.ignoreFiles}")
    String ignoreFiles;
    @Autowired
    GitService gitService;

    Set<String> ignoreFileSet;

    @PostConstruct
    public void init(){
        ignoreFileSet = new HashSet<>();
        Collections.addAll(ignoreFileSet, ignoreFiles.split("\\|"));
    }

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

        Stream<Path> list;
        try {
            list = Files.list(getFilePath(dirPath));

            return list.filter(this::isNotIgnoreFile)
                    .map(p -> getDirectoryContent(dirPath, p))
                    .sorted(this::directoryContentsCompare)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new DocFileServiceException("해당 디렉토리를 찾을 수 없습니다.", e);
        }
    }

    private int directoryContentsCompare(DirectoryContent o1, DirectoryContent o2) {
        if (o1.getType() != o2.getType())
            return o1.getType() == DIRECTORY ? -1 : 1;
        else
            return o1.getText().compareTo(o2.getText());
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
