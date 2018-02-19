package com.epam.project.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A custom exception that occurs when an file or catalog already exist.
 *
 * Created by Aliaksandr_Khmurchyk on 16-Feb-18.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FileOrCatalogAlreadyExistException extends RuntimeException {

    public FileOrCatalogAlreadyExistException() {}

    public FileOrCatalogAlreadyExistException(String message) {
        super(message);
    }

    public FileOrCatalogAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOrCatalogAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public FileOrCatalogAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
                                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
