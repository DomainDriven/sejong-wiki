package io.github.devsejong.wiki.wiki;

import io.github.devsejong.wiki.SejongWikiApplication;
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
        String doc = wikiService.readFileAndParse("test.md");
        //<then>
        assertThat(doc, containsString("<li>Hello</li>"));
    }
}