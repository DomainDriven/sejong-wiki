package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.SejongWikiApplication;
import io.github.devsejong.wiki.document.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SejongWikiApplication.class)
@WebAppConfiguration
public class WikiServiceTest {

    @Autowired
    WikiService wikiService;

    @Test
    public void testReadFileAndParse() throws Exception {
        //<when>
        Document doc = wikiService.getDocument("test.md");
        //<then>
        assertThat(doc.getBody(), containsString("<li>Hello</li>"));
    }
}