package io.github.devsejong.wiki.parser;

import io.github.devsejong.wiki.SejongWikiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.github.devsejong.wiki.parser.DocumentType.MARKDOWN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SejongWikiApplication.class)
@WebAppConfiguration
public class CoreParserTest {

    @Autowired
    CoreParser parserService;

    @Test
    public void stestParse() throws Exception {
        // when
        String html = parserService.parse(MARKDOWN, "# 제목입니다.");
        //then
        assertThat(html, is("<h1>제목입니다.</h1>"));
    }

    @Test
    public void testGetDocType() throws Exception {
        DocumentType md = parserService.getDocType("md");
        assertThat(md, is(MARKDOWN));
    }
}