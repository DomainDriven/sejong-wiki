package io.github.devsejong.wiki.document;

public class DocFileServiceException extends RuntimeException {
    public DocFileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocFileServiceException(String message) {
        super(message);
    }
}
