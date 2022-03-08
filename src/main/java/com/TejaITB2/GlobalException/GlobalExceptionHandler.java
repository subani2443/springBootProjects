package com.TejaITB2.GlobalException;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> GlobalErrorHAndling(Exception exception){
		ErrorDetails errDtls = new ErrorDetails(new Date(), exception.getMessage(), "please check");
		return new ResponseEntity<ErrorDetails>(errDtls, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFounfException.class)
	public ResponseEntity<ErrorDetails> GlobalErrorHAndlingUserNotFound(Exception exception){
		ErrorDetails errDtls = new ErrorDetails(new Date(), exception.getMessage(), "please check");
		return new ResponseEntity<ErrorDetails>(errDtls, HttpStatus.BAD_REQUEST);
		
	}
}
