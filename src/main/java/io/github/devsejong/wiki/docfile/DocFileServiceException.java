package io.github.devsejong.wiki.docfile;

public class DocFileServiceException extends RuntimeException {
    public DocFileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocFileServiceException(String message) {
        super(message);
    }
}
