package tech.ada.school.mappers;

import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.entities.Aluno;

public class AlunoMapper {
    public static Aluno toEntity(AlunoDto alunoDto){
        return Aluno.builder()
                .nome(alunoDto.getNome())
                .cpf(alunoDto.getCpf())
                .email(alunoDto.getEmail())
                .turma(alunoDto.getTurma())
                .build();
    }
    public static AlunoDto toDto(Aluno aluno){
        return AlunoDto.builder()
                .nome(aluno.getNome())
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .email(aluno.getEmail())
                .turma(aluno.getTurma())
                .build();
    }
    public static AlunoDto toDto(Aluno aluno, String activity){
        return AlunoDto.builder()
                .nome(aluno.getNome())
                .id(aluno.getId())
                .cpf(aluno.getCpf())
                .email(aluno.getEmail())
                .turma(aluno.getTurma())
                .activity(activity)
                .build();
    }
}
