package arutala.backend.bookrecipe.util;

import arutala.backend.bookrecipe.util.exception.BadRequestException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        String message = e.getMessage();
        if (message == null) {
            message = ResponseMessage.Failed.INTERNAL_SERVER_ERROR;
        }

        if (e instanceof EntityNotFoundException || e instanceof NoSuchElementException) {
            return ResponseHandler.notFound(message);
        }

        if (e instanceof BadRequestException) {
            return ResponseHandler.badRequset(message);
        }

        if (e instanceof AccessDeniedException) {
            return ResponseHandler.send("Acess denied", HttpStatus.FORBIDDEN);
        }

        if (e instanceof MethodArgumentNotValidException) {
            List<String> errors = new ArrayList<>();
            ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().forEach(err -> {
                String propertyName = ((FieldError) err).getField();
                String error = err.getDefaultMessage();
                errors.add(String.format(error, propertyName));
            });

            return ResponseHandler.badRequset(ResponseMessage.Failed.DEFAULT, errors);
        }

        if (e instanceof EntityExistsException) {
            return ResponseHandler.conflict(message);
        }

        return ResponseHandler.internalServerError(message);
    }
}
