package io.github.devsejong.wiki.file;

/**
 * Created by sejongpark on 2016. 1. 28..
 */
public interface FileService {
    String read(String path);

    void make(String path, String body);

    void modify(String path, String body, String history);

    void delete(String path);
}
