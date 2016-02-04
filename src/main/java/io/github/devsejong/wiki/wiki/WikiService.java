package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.document.DirectoryContent;
import io.github.devsejong.wiki.document.DocFileService;
import io.github.devsejong.wiki.document.Document;
import io.github.devsejong.wiki.parser.DocumentType;
import io.github.devsejong.wiki.parser.CoreParser;
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
    public String parse(Document document){
        String path = document.getPath();
        DocumentType docType = CoreParser.getDocType(getFileExtension(path));
        String rawText = docFileService.read(path).getBody();
        return CoreParser.parse(docType, rawText);
    }

    public Document getDocument(String wikiPath){
        return docFileService.read(wikiPath);
    }

    public List<DirectoryContent> getDirContents(String path){
        return docFileService.getDirContents(path);
    }

    private static String getFileExtension(String wikiPath){
        String fileName = wikiPath.substring(wikiPath.lastIndexOf("/"));
        return fileName.substring(fileName.lastIndexOf("."));
    }

}