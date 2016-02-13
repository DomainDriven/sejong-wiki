package io.github.devsejong.wiki.document.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DocFilePathHolder {

    @Value("${wiki.workingDirectory:}")
    String workingDirectory;
    @Value("${wiki.defaultWorkingDirectory}")
    String defaultWorkingDirectory;

    public String getWorkingDirectory() {
        if (workingDirectory == null || workingDirectory.equals(""))
            return defaultWorkingDirectory;
        else
            return workingDirectory;
    }
}
