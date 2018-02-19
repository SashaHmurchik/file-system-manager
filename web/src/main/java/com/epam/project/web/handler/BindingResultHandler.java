package com.epam.project.web.handler;

import com.epam.project.web.dto.MessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

/**
 *  Util class for creation BindingResult
 *
 * Created by Aliaksandr_Khmurchyk on 16-Feb-18.
 */
public class BindingResultHandler {

    private BindingResultHandler(){}

    public static MessageResponseDto validEntity(BindingResult bindingResult) {
        MessageResponseDto messageResponseDto = new MessageResponseDto();
        if (bindingResult.hasErrors()) {
            String msg = bindingResult.getFieldError().getDefaultMessage();
            messageResponseDto.setMsg(msg);
            messageResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return messageResponseDto;
        }
        return null;
    }
}
