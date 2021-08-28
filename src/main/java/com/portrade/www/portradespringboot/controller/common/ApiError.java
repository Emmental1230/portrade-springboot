package com.portrade.www.portradespringboot.controller.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {

    private final String message;

    private final int status;

    ApiError(Throwable throwable, HttpStatus httpStatus) {
        this(throwable.getMessage(), httpStatus);
    }

    ApiError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus.value();
    }
}
