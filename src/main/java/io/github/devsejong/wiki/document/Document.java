package io.github.devsejong.wiki.document;

import lombok.Value;

/*
 * 당장에 도메인 형태로 발전시키고 싶지만... 우선은 VO형식으로 작성한다.
 */
@Value
public class Document {
    private String path;
    private String body;
}
