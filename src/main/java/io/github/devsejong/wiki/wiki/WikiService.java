package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.docfile.DirectoryContent;
import io.github.devsejong.wiki.parser.DocumentType;
import io.github.devsejong.wiki.parser.CoreParser;
import io.github.devsejong.wiki.docfile.DocFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WikiService {

    @Autowired
    private DocFileService docFileService;

    /**
     * path의 파일을 읽은 뒤 파싱 결과를 반환한다.
     *
     * @param wikiPath
     * @return
     */
    public String readFileAndParse(String wikiPath){
        String rawText = docFileService.read(wikiPath);
        return CoreParser.parse(DocumentType.MARKDOWN, rawText);
    }

    public List<DirectoryContent> getDirContents(String path){
        return docFileService.getDirContents(path);
    }

    //지금은 사용안함.. 삭제할까?
    private static String getFileExtension(String wikiPath){
        String fileName = wikiPath.substring(wikiPath.lastIndexOf("/"));
        return fileName.substring(fileName.lastIndexOf("."));
    }

}