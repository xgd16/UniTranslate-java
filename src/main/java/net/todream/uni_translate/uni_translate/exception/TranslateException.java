package net.todream.uni_translate.uni_translate.exception;

public class TranslateException extends RuntimeException {

    public TranslateException(String message) {
        super(message);
    }

    public TranslateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TranslateException(Throwable cause) {
        super(cause);
    }
    
}
