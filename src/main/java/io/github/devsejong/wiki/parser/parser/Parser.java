package io.github.devsejong.wiki.parser.parser;

public interface Parser {
    boolean isParsable(String fileExtension);

    String parse(String source);
}
