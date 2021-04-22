package kz.iitu.remont.exception.handler;

import kz.iitu.remont.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        log.error("Exception handled: "+ e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("RuntimeException", e.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("Exception handled: "+ e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("HttpRequestMethodNotSupportedException", "такого запроса для этого url нету");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    // all other exception
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleAnyException(Exception e) {
        log.error("Exception handled: "+ e.getMessage(), e);
        ErrorResponse errorResponse = new ErrorResponse("INTERNAL_SERVER_ERROR",
                "Произошла системсная ошибка. Обратитесь к админу.");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
