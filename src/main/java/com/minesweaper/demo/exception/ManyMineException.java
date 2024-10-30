package com.minesweaper.demo.exception;

public class ManyMineException extends IllegalArgumentException {

    public ManyMineException() {
    }

    public ManyMineException(String s) {
        super("ManyMineException with message: " + s);
    }

    public ManyMineException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManyMineException(Throwable cause) {
        super(cause);
    }
}
