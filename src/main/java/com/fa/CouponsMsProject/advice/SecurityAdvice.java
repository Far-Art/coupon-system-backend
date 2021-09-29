package com.fa.CouponsMsProject.advice;

import com.fa.CouponsMsProject.exceptions.SecurityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SecurityAdvice {

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleLogin(SecurityException e){
        return e.getMessage();
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleMissingHeaderValue(MissingRequestHeaderException e){
        try {
            return e.getMessage().toLowerCase().contains("authorization") ? "Invalid token, please log in to get new one" : e.getMessage();
        } catch (Exception exception) {
            return "Token is not provided";
        }
    }
}