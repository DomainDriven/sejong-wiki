package io.github.devsejong.wiki.parser.docparser;

public interface Parser {
    boolean isParsable(String fileExtension);

    String parse(String source);
}
