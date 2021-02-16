package pl.moras.beersapi.controllers;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.moras.beersapi.exceptions.ObjectAlreadyExists;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController{

    @ExceptionHandler(value = ObjectAlreadyExists.class)
    public ResponseEntity<Map> handleAlreadyExists(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap("message", exception.getMessage()));
    }

    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<Map> handleNotFound(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("message", exception.getMessage()));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Map> handleValueError(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap("message", exception.getMessage()));
    }


}
