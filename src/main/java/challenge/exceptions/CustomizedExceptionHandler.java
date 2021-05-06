package challenge.exceptions;

import challenge.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Date;

@ControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(IncompleteBodyException.class)
    public ResponseEntity<ErrorDto> generateIncompleteBodyErrorResponse(IncompleteBodyException ex) {
        var error = new ErrorDto();
        error.setDate(new Date());
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
