package io.github.devsejong.wiki.docfile;

import java.util.List;

/**
 * Created by sejongpark on 2016. 1. 28..
 */
public interface DocFileService {
    String read(String path);

    void make(String path, String body);

    void modify(String path, String body, String history);

    void delete(String path);

    //FIXME 이름이 마음에 들지 않아 수정하자!
    List<DirectoryContent> getDirContents(String path);
}
