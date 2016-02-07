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
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SejongWikiApplication.class)
@WebAppConfiguration
public class WikiServiceTest {

    @Autowired
    WikiService wikiService;

    @Test
    public void testRead() throws Exception {
        //<when>
        Document doc = wikiService.getDocument("test.md");
        //<then>
        assertThat(doc.getBody(), containsString("<li>Hello</li>"));
    }

    @Test
    public void testGetFileExtension(){
        //<when>
        String fileExtension = WikiService.getFileExtension("aaa/bbbb/cccc.md");
        //<then>
        assertThat(fileExtension, is("md"));

        //<when>
        String fileExtension2 = WikiService.getFileExtension("dummy.md");
        //<then>
        assertThat(fileExtension2, is("md"));
    }
}