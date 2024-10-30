package com.minesweaper.demo.exception;

public class CellOpenedException extends RuntimeException {

    public CellOpenedException() {
    }

    public CellOpenedException(String s) {
        super("CellOpenedException with message: " + s);
    }

    public CellOpenedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CellOpenedException(Throwable cause) {
        super(cause);
    }
}