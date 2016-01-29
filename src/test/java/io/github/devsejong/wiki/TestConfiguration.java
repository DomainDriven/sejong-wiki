package io.github.devsejong.wiki;

import io.github.devsejong.wiki.file.git.GitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TestConfiguration {

    //테스트 환경에서는 GIT을 사용할 수 없으므로 아래처럼 Mock객체를 생성함.
    @Bean
    public GitService gitService(){
        return new GitService() {
            @Override
            public void update() {
                // doNothing!!
                log.info("gitServiec update 호출됨");
            }
        };
    }

}
