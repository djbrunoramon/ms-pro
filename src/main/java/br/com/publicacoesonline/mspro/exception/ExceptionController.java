package br.com.publicacoesonline.mspro.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({
            DataIntegrityViolationException.class
    })
    public ResponseEntity dataIntegrityViolationException(Exception ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
