package se.lexicon.g45_todo_api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, "Malformed JSON request!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException ex){

        APIError apiError= new APIError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(DataDuplicateException.class)
    public ResponseEntity<Object> dataDuplicateException(DataDuplicateException ex){

        APIError apiError= new APIError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgument(IllegalArgumentException ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    @ExceptionHandler(    IllegalStateException.class) // for ambiguous URI
    public ResponseEntity<Object> illegalArgument(IllegalStateException ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolation(ConstraintViolationException ex) {
        APIError apiError = new APIError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
    }




}
