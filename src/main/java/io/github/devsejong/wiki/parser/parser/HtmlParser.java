package io.github.devsejong.wiki.parser.parser;

public class HtmlParser implements Parser {

    @Override
    public boolean isParsable(String fileExtension) {
        return fileExtension.equalsIgnoreCase("html");
    }

    @Override
    public String parse(String source) {
        return source;
    }
}
