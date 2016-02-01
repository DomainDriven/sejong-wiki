package io.github.devsejong.wiki.parser;

import io.github.devsejong.wiki.parser.operator.HtmlParser;
import io.github.devsejong.wiki.parser.operator.MarkdownParser;
import io.github.devsejong.wiki.parser.operator.Parser;
import io.github.devsejong.wiki.parser.operator.TextParser;

public enum DocumentType {
    MARKDOWN(MarkdownParser.class),
    HTML(HtmlParser.class),
    TEXT(TextParser.class);

    private Class<? extends Parser> parserClass;

    DocumentType(Class<? extends Parser> parserClass) {
        this.parserClass = parserClass;
    }

    public Class<? extends Parser> getParserClass() {
        return parserClass;
    }
}
