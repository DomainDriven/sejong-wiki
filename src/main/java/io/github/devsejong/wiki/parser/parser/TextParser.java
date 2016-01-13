package io.github.devsejong.wiki.parser.parser;

public class TextParser implements Parser {

    @Override
    public boolean isParsable(String fileExtension) {
        return fileExtension.equalsIgnoreCase("txt");
    }

    @Override
    public String parse(String source) {
        return source;
    }
}
