package com.quangsang.springbaseexample.exception;

import com.quangsang.springbaseexample.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.Entity;
import java.util.Date;

@ControllerAdvice
/*extends ResponseEntityExceptionHandler => thi moi an code handle exception cua spring*/
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*NOT_FOUND : LOI 404 NOT FOUND*/
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ErrorCode.NOT_FOUND, new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ClientException.class})
    protected ResponseEntity<Object> handleServerError(ClientException ex, WebRequest request) {
        logger.error("error: ", ex);
        ErrorDetailPlus res = ErrorDetailPlus.build(ex);

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    /*INTERNAL_SERVER_ERROR : LOI SERVER 500*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        //ErrorDetails errorDetails = new ErrorDetails(ErrorCode.NOT_FOUND, new Date(), ex.getMessage(), request.getDescription(false));
        ErrorDetailPlus errorDetails = ErrorDetailPlus.build(ErrorCode.SERVER_ERROR,request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> ArgumentException(Exception ex, WebRequest request) {
        //ErrorDetails errorDetails = new ErrorDetails(ErrorCode.NOT_FOUND, new Date(), ex.getMessage(), request.getDescription(false));
        ErrorDetailPlus errorDetails = ErrorDetailPlus.build(ErrorCode.SERVER_ERROR,request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
