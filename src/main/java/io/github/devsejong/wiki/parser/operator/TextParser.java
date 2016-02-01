package io.github.devsejong.wiki.parser.operator;

// Text문자열일 경우에 <br>로 변경하여 준다.
public class TextParser implements Parser {

    @Override
    public boolean isParsable(String fileExtension) {
        return fileExtension.equalsIgnoreCase("txt");
    }

    @Override
    public String parse(String source) {
        return source.replaceAll("\r\n|\n|\r", "<br>");
    }
}
