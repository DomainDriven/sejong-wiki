package io.github.devsejong.wiki.parser;

import io.github.devsejong.wiki.parser.parser.Parser;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParserService {
    private List<DocType> docTypes;
    private Map<DocType, Parser> parsers;

    @PostConstruct
    public void init() throws IllegalAccessException, InstantiationException {
        docTypes = Arrays.asList(DocType.values());

        parsers = new HashMap<>();
        for (DocType docType : docTypes)
            parsers.put(docType, docType.getParserClass().newInstance());
    }

    public String parse(DocType type, String source) {
        return parsers.get(type).parse(source);
    }

    public DocType getDocType(String fileExtension) {

        for (DocType docType : parsers.keySet())
            if (parsers.get(docType).isParsable(fileExtension))
                return docType;

        throw new ParserException("Cannot find Paser mapped with file extension. fileExtension : " + fileExtension);
    }
}
