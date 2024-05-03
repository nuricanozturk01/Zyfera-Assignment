package nuricanozturk.dev.management.student.exceptionhandler;

import nuricanozturk.dev.management.student.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

@ControllerAdvice
public class ValidationExceptionHandler
{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage<?>> customValidationErrorHandling(MethodArgumentNotValidException exception, WebRequest request)
    {
        var errors = new HashMap<String, Object>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(new ResponseMessage<>("Invalid Input", HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }
}