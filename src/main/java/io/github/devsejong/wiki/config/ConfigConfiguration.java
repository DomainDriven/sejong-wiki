package io.github.devsejong.wiki.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//설정을 설정하는 클래스..^_^; 좋은이름인가?
@EnableAsync
@EnableScheduling
@PropertySource("classpath:wikiConfig.properties")
@Configuration
public class ConfigConfiguration {
}
