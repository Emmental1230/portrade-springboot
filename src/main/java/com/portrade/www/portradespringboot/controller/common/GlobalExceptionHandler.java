package com.portrade.www.portradespringboot.controller.common;

import com.portrade.www.portradespringboot.error.NotFoundException;
import com.portrade.www.portradespringboot.error.ServiceRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import static com.portrade.www.portradespringboot.controller.common.ApiResult.ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ResponseEntity<ApiResult<?>> response(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(throwable, status), headers, status);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotFoundException(Exception e) {
        return response(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            IllegalStateException.class, IllegalArgumentException.class,
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        return response(e, HttpStatus.BAD_REQUEST);
    }

    /**
     * 정의한 비즈니스 예외가 발생하는 경우 Exception Handling
     */
    @ExceptionHandler(ServiceRuntimeException.class)
    public ResponseEntity<?> handleServiceRuntimeException(ServiceRuntimeException e) {
        if (e instanceof NotFoundException) {
            return response(e, HttpStatus.NOT_FOUND);
        }

        log.warn("Unexpected service exception occurred: {}", e.getMessage(), e);
        return response(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 예측하지 못한 API Server Exception Handling
     */
    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        return response(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
