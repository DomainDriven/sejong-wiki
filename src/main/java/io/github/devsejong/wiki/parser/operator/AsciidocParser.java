package io.github.devsejong.wiki.parser.operator;


import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Slf4j
public class AsciidocParser implements Parser {
    private ScriptEngine engine;
    private static final String ASCIIDOC_JS_PATH = "jslib/asciidoctor-all.min.js";

    public AsciidocParser() {
        engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            log.debug("AsciiDoc파서 초기화 시작");
            File file = new File(getClass().getClassLoader().getResource(ASCIIDOC_JS_PATH).getFile());
            engine.eval(new FileReader(file));
            engine.eval("var options = Opal.hash({attributes: ['showtitle']});");

            log.debug("AsciiDoc파서 초기화 성공");
        } catch (ScriptException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isParsable(String fileExtension) {
        return fileExtension.equalsIgnoreCase("adoc");
    }

    @Override
    public String parse(String source) {
        engine.put("content", source);
        try {
            engine.eval("var html = Opal.Asciidoctor.$convert(content, options);");
            Object html = engine.get("html");

            // 에러가 발생할경우 어떻게 대응하지???
            return html.toString();
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

}
