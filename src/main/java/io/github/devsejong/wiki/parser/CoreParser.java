package io.github.devsejong.wiki.parser;

import io.github.devsejong.wiki.parser.operator.Parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CoreParser {
    private static Map<DocumentType, Parser> parsers;

    static {
        List<DocumentType> documentTypes = Arrays.asList(DocumentType.values());

        parsers = new HashMap<>();
        for (DocumentType documentType : documentTypes)
            try {
                parsers.put(documentType, documentType.getParserClass().newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ParserException("초기화 도중 에러 발생", e);
            }
    }

    public static String parse(DocumentType type, String source) {
        return parsers.get(type).parse(source);
    }

    public static DocumentType getDocType(String fileExtension) {
        for (DocumentType documentType : parsers.keySet())
            if (parsers.get(documentType).isParsable(fileExtension))
                return documentType;

        throw new ParserException("Cannot find Paser mapped with file extension. fileExtension : " + fileExtension);
    }
}
