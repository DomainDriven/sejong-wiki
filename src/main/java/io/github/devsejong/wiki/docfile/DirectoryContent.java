package io.github.devsejong.wiki.docfile;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DirectoryContent {
    private String id;
    private String text;
    private FileType type;
    @JsonProperty("children")
    private boolean hasChildren;
}
