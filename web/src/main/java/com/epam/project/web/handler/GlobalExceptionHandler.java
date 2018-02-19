package com.epam.project.web.handler;

import com.epam.project.dao.exception.EntityNotFoundException;
import com.epam.project.manager.exception.CatalogIsNotExistException;
import com.epam.project.manager.exception.FileOrCatalogAlreadyExistException;
import com.epam.project.web.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

/**
 * This controller is used to handle all exceptions.
 *
 * Created by Aliaksandr_Khmurchyk on 16-Feb-18.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handlerException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(value = FileOrCatalogAlreadyExistException.class)
    public ResponseEntity<MessageResponseDto> fileOrCatalogAlreadyExist(FileOrCatalogAlreadyExistException ex) {
        return messageResponse(ex);
    }

    @ExceptionHandler(value = CatalogIsNotExistException.class)
    public ResponseEntity<MessageResponseDto> catalogNotFound(CatalogIsNotExistException ex) {
        return messageResponse(ex);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<MessageResponseDto> entityNotFound(EntityNotFoundException ex) {
        return messageResponse(ex);
    }

    private ResponseEntity<MessageResponseDto> messageResponse(Exception ex) {
        HttpStatus httpStatus = resolveAnnotatedResponseStatus(ex);
        MessageResponseDto errorResponse = new MessageResponseDto();
        errorResponse.setMsg(ex.getMessage());
        errorResponse.setStatusCode(httpStatus.value());
        return new ResponseEntity<>(errorResponse, httpStatus);
    }


    private HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
        ResponseStatus annotation =
                findMergedAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
