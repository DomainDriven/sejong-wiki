package io.github.devsejong.wiki;

import io.github.devsejong.wiki.wiki.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SejongWikiApplication {

    @Autowired
    private WikiService wikiService;

	public static void main(String[] args) {
		SpringApplication.run(SejongWikiApplication.class, args);
	}

}
