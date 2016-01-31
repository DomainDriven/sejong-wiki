package io.github.devsejong.wiki.docfile;


import io.github.devsejong.wiki.SejongWikiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SejongWikiApplication.class})
@WebAppConfiguration
public class DocFileServiceImplTest {

    @Autowired
    DocFileService docFileService;

    @Test
    public void testRead() throws Exception {
    }

    @Test
    public void testMake() throws Exception {

    }

    @Test
    public void testModify() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetDirContents() throws Exception {
        List<DirectoryContent> dirContents = docFileService.getDirContents("");
        System.out.println(dirContents);
        System.out.println(dirContents);
    }
}