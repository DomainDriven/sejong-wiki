package io.github.devsejong.wiki.parser;

public class ParserException extends RuntimeException {
    public ParserException(String message) {
        super(message);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
