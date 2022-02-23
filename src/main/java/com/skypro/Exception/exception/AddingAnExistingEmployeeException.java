package com.skypro.Exception.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AddingAnExistingEmployeeException extends RuntimeException {

}
