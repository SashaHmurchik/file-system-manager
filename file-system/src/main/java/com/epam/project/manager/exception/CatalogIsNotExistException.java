package com.epam.project.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A custom exception that occurs when catalog is not exist.
 *
 * Created by Aliaksandr_Khmurchyk on 16-Feb-18.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CatalogIsNotExistException extends RuntimeException {
    public CatalogIsNotExistException() {}

    public CatalogIsNotExistException(String message) {
        super(message);
    }

    public CatalogIsNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CatalogIsNotExistException(Throwable cause) {
        super(cause);
    }

    public CatalogIsNotExistException(String message, Throwable cause, boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
