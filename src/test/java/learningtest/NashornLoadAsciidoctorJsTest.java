package learningtest;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class NashornLoadAsciidoctorJsTest {

    @Test
    public void simpleNashornTest() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }

    //@Ignore("script를 읽는데 시간이 너무 오래 걸림. ignore 처리함.")
    @Test
    public void nashornLoadJsTest() throws ScriptException, FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        File file = new File(getClass().getClassLoader().getResource("jslib/asciidoctor-all.min.js").getFile());
        engine.eval(new FileReader(file));
        engine.put("content",  "http://asciidoctor.org[*Asciidoctor*] " +
                "running on http://opalrb.org[_Opal_] " +
                "brings AsciiDoc to the browser!");
        engine.eval("var options = Opal.hash({attributes: ['showtitle']});");
        engine.eval("var html = Opal.Asciidoctor.$convert(content, options);");

        Object obj = engine.get("html");


        System.out.println(obj);
    }
}
