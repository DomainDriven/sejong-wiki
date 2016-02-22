package io.github.devsejong.wiki.document;


import io.github.devsejong.wiki.SejongWikiApplication;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SejongWikiApplication.class})
@WebAppConfiguration
public class DocFileServiceTest {

    @Autowired
    DocFileService service;

    @Test
    public void testRead() throws Exception {
        //when
        Document document = service.read("test.md");

        //then
        assertThat(document.getBody(), CoreMatchers.containsString("- 안녕"));
        assertThat(document.getBody(), CoreMatchers.containsString("- Hello"));
    }

    @Test(expected = DocumentNotFoundException.class)
    public void testWhenReadNotExistFile_throw(){
        service.read("not/readable/path");
    }

    @Test(expected = DocumentNotFoundException.class)
    public void testWhenReadFolder_throw(){
        service.read("dummy");
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
        // root 폴더를 읽었을 경우에는 폴더가 우선순위로, 그 다음 파일이 노출되어야 한다.

        //given
        //when
        List<DirectoryContent> dirContents = service.getDirContents("");

        //then
        assertThat(dirContents.get(0).getLabel(), CoreMatchers.is("dummy"));
        assertThat(dirContents.get(1).getLabel(), CoreMatchers.is("markup"));
        assertThat(dirContents.get(2).getLabel(), CoreMatchers.is("test.md"));
    }

}