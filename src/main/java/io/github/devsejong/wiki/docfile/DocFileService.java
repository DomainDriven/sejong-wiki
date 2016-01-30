package io.github.devsejong.wiki.docfile;

/**
 * Created by sejongpark on 2016. 1. 28..
 */
public interface DocFileService {
    String read(String path);

    void make(String path, String body);

    void modify(String path, String body, String history);

    void delete(String path);
}
