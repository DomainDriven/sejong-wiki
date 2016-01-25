package io.github.devsejong.wiki.config;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Log
@Configuration
public class MvcConfiguration extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
    @Value("${wiki.working.directory}")
    String workingDirectory;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/w/**/_attachments/**")
                .addResourceLocations("file:" + workingDirectory);
    }
}
