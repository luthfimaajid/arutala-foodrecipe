package arutala.backend.bookrecipe.util;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        String message = e.getMessage();

        if (e instanceof EntityNotFoundException) {
            return ResponseHandler.notFound(message);
        }

        if (e instanceof BadRequestException) {
            return ResponseHandler.badRequset(message);
        }

        return ResponseHandler.internalServerError(message);
    }




}
