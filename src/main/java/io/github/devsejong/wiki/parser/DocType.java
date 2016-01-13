package io.github.devsejong.wiki.parser;

import io.github.devsejong.wiki.parser.parser.HtmlParser;
import io.github.devsejong.wiki.parser.parser.MarkdownParser;
import io.github.devsejong.wiki.parser.parser.Parser;
import io.github.devsejong.wiki.parser.parser.TextParser;

public enum DocType {
    MARKDOWN(MarkdownParser.class),
    HTML(HtmlParser.class),
    TEXT(TextParser.class);

    private Class<? extends Parser> parserClass;

    DocType(Class<? extends Parser> parserClass) {
        this.parserClass = parserClass;
    }

    public Class<? extends Parser> getParserClass() {
        return parserClass;
    }
}
