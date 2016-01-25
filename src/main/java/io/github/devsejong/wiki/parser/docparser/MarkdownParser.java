package io.github.devsejong.wiki.parser.docparser;

import org.pegdown.PegDownProcessor;

public class MarkdownParser implements Parser {
    private final PegDownProcessor markdownParser;

    public MarkdownParser() {
        markdownParser = new PegDownProcessor();
    }

    @Override
    public boolean isParsable(String fileExtension) {
        return fileExtension.equalsIgnoreCase("md");
    }

    @Override
    public String parse(String source) {
        return markdownParser.markdownToHtml(source);
    }
}
