package nuricanozturk.dev.management.student.exceptionhandler;

import nuricanozturk.dev.management.student.dto.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;

/**
 * Exception handler for validation errors
 */
@ControllerAdvice
public class ValidationExceptionHandler
{
    /**
     * Handles validation errors.
     * This method handle the jakarta validation exceptions and return a response entity with the error message.
     *
     * @param exception The exception to handle
     * @param request   The request
     * @return The response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage<?>> customValidationErrorHandling(MethodArgumentNotValidException exception, WebRequest request)
    {
        var errors = new HashMap<String, Object>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return new ResponseEntity<>(new ResponseMessage<>("Invalid Input", HttpStatus.BAD_REQUEST.value(), errors), HttpStatus.BAD_REQUEST);
    }
}