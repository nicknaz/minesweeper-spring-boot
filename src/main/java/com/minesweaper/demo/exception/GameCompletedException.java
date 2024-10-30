package com.minesweaper.demo.exception;

public class GameCompletedException extends RuntimeException {

    public GameCompletedException() {
    }

    public GameCompletedException(String s) {
        super("GameCompletedException with message: " + s);
    }

    public GameCompletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameCompletedException(Throwable cause) {
        super(cause);
    }
}
