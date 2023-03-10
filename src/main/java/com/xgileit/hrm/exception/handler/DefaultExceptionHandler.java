package com.xgileit.hrm.exception.handler;

import com.xgileit.hrm.exception.*;
import com.xgileit.hrm.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequest.class
    })
    public ResponseEntity<ErrorResponse> badRequestException(BadRequest exception,
            HttpServletRequest request) throws IOException {
        ErrorResponse exceptionResponse =
                new ErrorResponse(new Timestamp(System.currentTimeMillis()),
                        HttpStatus.BAD_REQUEST.value(), "Bad Request", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            UnAuthorized.class
    })
    public ResponseEntity<ErrorResponse> unAuthorizedException(UnAuthorized exception,
            HttpServletRequest request) throws IOException {
        ErrorResponse exceptionResponse =
                new ErrorResponse(new Timestamp(System.currentTimeMillis()),
                        HttpStatus.UNAUTHORIZED.value(), "Unauthorizes", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            Forbidden.class
    })
    public ResponseEntity<ErrorResponse> badRequestException(Forbidden exception,
            HttpServletRequest request) throws IOException {
        ErrorResponse exceptionResponse =
                new ErrorResponse(new Timestamp(System.currentTimeMillis()),
                        HttpStatus.FORBIDDEN.value(), "Forbidden", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFound.class
    })
    public ResponseEntity<ErrorResponse> notFoundErrorException(NotFound exception,
            HttpServletRequest request) throws IOException {
        ErrorResponse exceptionResponse =
                new ErrorResponse(new Timestamp(System.currentTimeMillis()),
                        HttpStatus.NOT_FOUND.value(), "Not Found", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            AlreadyInUsed.class
    })
    public ResponseEntity<ErrorResponse> alreadyInUsedException(AlreadyInUsed exception,
            HttpServletRequest request) throws IOException {
        ErrorResponse exceptionResponse =
                new ErrorResponse(new Timestamp(System.currentTimeMillis()),
                        HttpStatus.CONFLICT.value(), "Conflict", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(exceptionResponse, HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            InternalServerError.class
    })
    public ResponseEntity<ErrorResponse> internalServerErrorException(InternalServerError exception,
            HttpServletRequest request) throws IOException {
        ErrorResponse exceptionResponse = new ErrorResponse(
                new Timestamp(System.currentTimeMillis()), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error", exception.getMessage());
        return new ResponseEntity<ErrorResponse>(exceptionResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<ErrorResponse> genericException(Exception exception,
            HttpServletRequest request) throws IOException {
        ErrorResponse exceptionResponse = new ErrorResponse(
                new Timestamp(System.currentTimeMillis()), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Something went wrong while processing your request. Please try after some time."
                        + " If issues persists, please contact our customer support team.");
        return new ResponseEntity<ErrorResponse>(exceptionResponse,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
