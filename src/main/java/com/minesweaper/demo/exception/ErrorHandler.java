package com.minesweaper.demo.exception;

import com.minesweaper.demo.exception.NotFoundedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<String, String> validationException(final ConstraintViolationException e) {
        log.debug("Получено исключение: " + e.getMessage());
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler({ManyMineException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<String, String> manyMinesException(final ManyMineException e) {
        log.debug("Получено исключение: " + e.getMessage());
        return Map.of("error", e.getMessage());
    }


    @ExceptionHandler({NotFoundedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<String, String> notFoundedException(final NotFoundedException e) {
        log.debug("Получено исключение: " + e.getMessage());
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler({GameCompletedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<String, String> gameCompletedException(final GameCompletedException e) {
        log.debug("Получено исключение: " + e.getMessage());
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler({CellOpenedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private Map<String, String> cellOpenedException(final CellOpenedException e) {
        log.debug("Получено исключение: " + e.getMessage());
        return Map.of("error", e.getMessage());
    }


}
