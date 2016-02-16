package io.github.devsejong.wiki.parser;

import io.github.devsejong.wiki.parser.operator.*;

public enum DocumentType {
    MARKDOWN(MarkdownParser.class),
    HTML(HtmlParser.class),
    TEXT(TextParser.class),
    ASCIIDOC(AsciidocParser.class);

    private Class<? extends Parser> parserClass;

    DocumentType(Class<? extends Parser> parserClass) {
        this.parserClass = parserClass;
    }

    public Class<? extends Parser> getParserClass() {
        return parserClass;
    }
}
