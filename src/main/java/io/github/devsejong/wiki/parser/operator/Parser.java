package io.github.devsejong.wiki.parser.operator;

public interface Parser {
    boolean isParsable(String fileExtension);
    String parse(String source);
}
