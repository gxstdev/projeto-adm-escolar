package tech.ada.school.mappers;

import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.domain.entities.Professor;

public class ProfessorMapper {
    public static Professor toEntity(ProfessorDto professorDto){
        return Professor.builder()
                .nome(professorDto.getNome())
                .cpf(professorDto.getCpf())
                .email(professorDto.getEmail())
                .build();
    }
    public static ProfessorDto toDto(Professor professor){
        return ProfessorDto.builder()
                .nome(professor.getNome())
                .id(professor.getId())
                .cpf(professor.getCpf())
                .email(professor.getEmail())
                .build();
    }
}
