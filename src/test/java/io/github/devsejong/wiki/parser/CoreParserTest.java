package io.github.devsejong.wiki.parser;

import org.junit.Test;

import static io.github.devsejong.wiki.parser.DocumentType.MARKDOWN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CoreParserTest {


    @Test
    public void testParse() throws Exception {
        // when
        String html = CoreParser.parse(MARKDOWN, "# 제목입니다.");
        //then
        assertThat(html, is("<h1>제목입니다.</h1>"));
    }

    @Test
    public void testGetDocType() throws Exception {
        DocumentType md = CoreParser.getDocType("md");
        assertThat(md, is(MARKDOWN));
    }

}