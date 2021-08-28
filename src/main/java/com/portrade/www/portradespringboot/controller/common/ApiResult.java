package com.portrade.www.portradespringboot.controller.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class ApiResult<T> {

    private final boolean success;
    private final T response;
    private final ApiError error;

    public static <T> ApiResult<T> OK(T response) {
        return new ApiResult(true, response, null);
    }

    public static ApiResult<?> ERROR(Throwable throwable, HttpStatus httpStatus) {
        return new ApiResult(false, null, new ApiError(throwable, httpStatus));
    }

    public static ApiResult<?> ERROR(String errorMessage, HttpStatus httpStatus) {
        return new ApiResult(false, null, new ApiError(errorMessage, httpStatus));
    }
}
