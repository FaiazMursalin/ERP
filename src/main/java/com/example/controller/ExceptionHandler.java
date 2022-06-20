package com.example.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler extends RuntimeException{

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String showAccessDeniedException(){
        return "error/403";
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    public String notFound(){
        return "error/404";
    }
}
