package io.github.devsejong.wiki;

import io.github.devsejong.wiki.document.git.GitService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestConfiguration {

    @Bean
    public GitService gitService(){
        return new GitService() {
//            public void update() {
//
//            }
        };
    }
}
