package io.github.devsejong.wiki.data;

import java.io.File;

// 자유도가
public interface DataService {
    void make(String path, String body);

    // 수정한다.
    void modify(String path, String body, String history);

    //해당 위치의 파일을 제거한다.
    void delete(String path);

    //깃의 정보도 반환하여야 한다.
    String read(String path);

    File readFile(String path);
}
