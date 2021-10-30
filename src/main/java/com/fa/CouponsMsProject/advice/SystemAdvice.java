package com.fa.CouponsMsProject.advice;

import com.fa.CouponsMsProject.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class SystemAdvice {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleCustomException(CustomException e){
        return e.getMessage();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(CustomException e){
        return e.getMessage();
    }
}