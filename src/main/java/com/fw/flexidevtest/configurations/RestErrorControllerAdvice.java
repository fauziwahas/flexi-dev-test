package com.fw.flexidevtest.configurations;

import com.fasterxml.jackson.core.JsonParseException;
import com.fw.flexidevtest.constants.MessageConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorControllerAdvice {

  @ExceptionHandler({IllegalArgumentException.class})
  @ResponseBody
  public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseBody
  public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return ResponseEntity.badRequest().body(MessageConstants.METHOD_ARGUMENT_NOT_VALID);
  }

  @ExceptionHandler({JsonParseException.class})
  @ResponseBody
  public ResponseEntity<?> handleJsonParseException(Exception e) {
    return ResponseEntity.badRequest().body("JSON parse error");
  }

}
