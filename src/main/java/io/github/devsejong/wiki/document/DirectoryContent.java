package io.github.devsejong.wiki.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DirectoryContent {
    private String id;
    private String label;
    @JsonProperty("load_on_demand")
    private boolean hasChildren;
}
