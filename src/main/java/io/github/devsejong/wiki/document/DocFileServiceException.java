package io.github.devsejong.wiki.document;

public class DocFileServiceException extends RuntimeException {
    public DocFileServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocFileServiceException(String message) {
        super(message);
    }

    public DocFileServiceException(Throwable cause) {
        super(cause);
    }

    public static class DirectoryNotFoundException extends DocFileServiceException {
        public DirectoryNotFoundException(Throwable cause) {
            super("디렉토리를 찾을 수 없습니다", cause);
        }
    }

    public static class FailToReadFileException extends DocFileServiceException {
        public FailToReadFileException(Throwable cause) {
            super("파일을 읽지 못하였습니다.", cause);
        }
    }

    public static class DocumentNotFoundException extends DocFileServiceException {
        public DocumentNotFoundException() {
            super("파일을 찾지 못하였습니다.");
        }
    }
}
