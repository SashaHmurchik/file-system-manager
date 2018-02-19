package com.epam.project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  Using a class to display a custom message for client
 * and to display a httpCode {@link org.springframework.http.HttpStatus} as an integer.
 * Created by Aliaksandr_Khmurchyk on 16-Feb-18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto implements Serializable {

    private String msg;
    private int statusCode;
}
