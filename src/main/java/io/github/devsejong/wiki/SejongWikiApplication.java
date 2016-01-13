package io.github.devsejong.wiki;

import io.github.devsejong.wiki.core.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SejongWikiApplication implements ApplicationRunner {
    @Autowired
    private WikiService wikiService;

	public static void main(String[] args) {
		SpringApplication.run(SejongWikiApplication.class, args);
	}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        wikiService.initWiki();
    }
}
