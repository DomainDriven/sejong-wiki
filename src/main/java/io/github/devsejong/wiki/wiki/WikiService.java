package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.document.DirectoryContent;
import io.github.devsejong.wiki.document.DocFileService;
import io.github.devsejong.wiki.document.Document;
import io.github.devsejong.wiki.parser.CoreParser;
import io.github.devsejong.wiki.parser.DocumentType;
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
     * @param document
     * @return
     */
    public String parse(Document document){
        String path = document.getPath();
        DocumentType docType = CoreParser.getDocType(getFileExtension(path));
        String rawText = docFileService.read(path).getBody();
        return CoreParser.parse(docType, rawText);
    }
    public void save(String path, String body, String comment){
        docFileService.modify(path, body, comment);
    }

    public Document getDocument(String wikiPath){
        return docFileService.read(wikiPath);
    }

    public List<DirectoryContent> getDirContents(String path){
        return docFileService.getDirContents(path);
    }

    static String getFileExtension(String wikiPath){

        int pathSeperatorIndex = wikiPath.lastIndexOf("/");

        String fileName;
        if(pathSeperatorIndex != -1)
            fileName = wikiPath.substring(pathSeperatorIndex);
        else
            fileName = wikiPath;

        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}