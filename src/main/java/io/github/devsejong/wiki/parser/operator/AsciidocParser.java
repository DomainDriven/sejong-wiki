package io.github.devsejong.wiki.parser.operator;

import org.asciidoctor.Asciidoctor;

import java.util.HashMap;
import java.util.Map;

public class AsciidocParser implements Parser {
    private final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

    @Override
    public boolean isParsable(String fileExtension) {
        return fileExtension.equalsIgnoreCase("adoc");
    }

    @Override
    public String parse(String source) {
        Map<String, Object> options = new HashMap<>();
        return asciidoctor.convert(source, options);
    }
}
