package com.minesweaper.demo.exception;

public class NotFoundedException extends IllegalArgumentException {

    public NotFoundedException() {
    }

    public NotFoundedException(String s) {
        super("NotFoundedException with message: " + s);
    }

    public NotFoundedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundedException(Throwable cause) {
        super(cause);
    }
}
