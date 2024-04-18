package tech.ada.school.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import tech.ada.school.domain.dto.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data

public class ErrorResponse {
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Collection<ErrorMessage> errors;

    private ErrorResponse(String message) {
        this.message = message;
    }

    private ErrorResponse(String message, Collection<ErrorMessage> errors) {
        this.message = message;
        this.errors = errors;
    }
    public static ErrorResponse createFromException(NotFoundException ex){
        return new ErrorResponse(String.format("No record of %s was found for value %s",
                ex.getClazz().getSimpleName(), ex.getId()));
    }
    public static ErrorResponse createFromException(MethodArgumentNotValidException ex) {

        List<FieldError> list = ex.getFieldErrors();
        List<ErrorMessage> errors = new ArrayList<>();

        list.forEach(it -> errors.add(new ErrorMessage(it.getField(), it.getDefaultMessage())));
        return new ErrorResponse("Validation errors", errors);
    }
}
