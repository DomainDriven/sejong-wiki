package io.github.devsejong.wiki.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//설정을 설정하는 클래스..^_^; 좋은이름인가?
@Configuration
@PropertySource("classpath:wikiConfig.properties")
public class ConfigConfiguration {
}
