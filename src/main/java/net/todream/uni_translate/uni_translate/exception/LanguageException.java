package net.todream.uni_translate.uni_translate.exception;

public class LanguageException extends RuntimeException {

    public LanguageException(String message) {
        super(message);
    }

    public LanguageException(String message, Throwable cause) {
        super(message, cause);
    }

    public LanguageException(Throwable cause) {
        super(cause);
    }
    
}
