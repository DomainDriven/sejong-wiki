package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.parser.DocType;
import io.github.devsejong.wiki.parser.ParserService;
import io.github.devsejong.wiki.docfile.DocFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WikiService {

    @Autowired
    private DocFileService docFileService;
    @Autowired
    private ParserService parserService;

    /**
     * path의 파일을 읽은 뒤 파싱 결과를 보여줍니다.
     *
     * @param wikiPath
     * @return
     */
    public String readFileAndParse(String wikiPath){
        String rawText = docFileService.read(wikiPath);
        return parserService.parse(DocType.MARKDOWN, rawText);
    }

    //지금은 사용안함.. 삭제할까?
    private static String getFileExtension(String wikiPath){
        String fileName = wikiPath.substring(wikiPath.lastIndexOf("/"));
        return fileName.substring(fileName.lastIndexOf("."));
    }
}