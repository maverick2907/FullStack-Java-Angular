package com.popularsafi.proyecto.apirest.config;

import com.popularsafi.proyecto.apirest.exception.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@AllArgsConstructor
public class RestExceptionHandling {

    private final MessageSource messageSource;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    ProblemDetail handleBadRequestException(BadRequestException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handleEntityNotFoundException(EntityNotFoundException exception) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        ProblemDetail problemDetail =ProblemDetail
                .forStatusAndDetail(HttpStatus
                        .UNPROCESSABLE_ENTITY,
                        "La entidad no se puede procesar porque tiene algunos errores");

        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<String> errors = new ArrayList<>();

        for (FieldError fe : fieldErrors) {
            String message = messageSource.getMessage(fe, Locale.getDefault());
            errors.add(message);
        }

        problemDetail.setProperty("errors", errors);
        return problemDetail;

    }


}
