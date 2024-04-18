package tech.ada.school.domain.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class NotFoundException extends Exception{
    private final Class clazz;
    private final String id;
}
