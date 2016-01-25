package io.github.devsejong.wiki.parser.docparser;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MarkdownParserTest {
    MarkdownParser parser = new MarkdownParser();

    @Test
    public void testParse() throws Exception {
        //given

        //when
        String str = parser.parse("# 제목입니다.");

        //then
        assertThat(str, is("<h1>제목입니다.</h1>"));
    }
}