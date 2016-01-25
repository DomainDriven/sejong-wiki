package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.parser.DocType;
import io.github.devsejong.wiki.parser.ParserService;
import io.github.devsejong.wiki.data.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class WikiService {

    @Autowired
    private DataService dataService;
    @Autowired
    private ParserService parserService;

    /**
     * path의 파일을 읽은 뒤 파싱 결과를 보여줍니다.
     *
     * @param wikiPath
     * @return
     */
    public String readFileAndParse(String wikiPath){
        String rawText = dataService.read(wikiPath);
        System.out.println(rawText);
        return parserService.parse(DocType.MARKDOWN, rawText);
    }

    /**
     * path의 파일을 읽은 뒤 문자열을 반환합니다.
     *
     * @param wikiPath
     * @return
     */
    public File readFile(String wikiPath){
        return dataService.readFile(wikiPath);
    }

    private static String getFileExtension(String wikiPath){
        String file = wikiPath.substring(wikiPath.lastIndexOf("/"));
        String extension = file.substring(file.lastIndexOf("."));

        return extension;
    }
}
