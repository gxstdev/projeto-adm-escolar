package tech.ada.school.service;

import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.dto.exception.NotFoundException;

import java.util.List;

public interface IAlunoService {
    AlunoDto criarAluno(AlunoDto aluno);
    List<AlunoDto> lerAlunos();
    AlunoDto buscarAlunoPorId(int id) throws NotFoundException;
    AlunoDto atualizarAluno(int id, AlunoDto aluno) throws NotFoundException;
    void deletarAluno(int id) throws NotFoundException;
    List<AlunoDto> buscarAlunoPorTurma(int turma) throws NotFoundException;
    List<AlunoDto> buscarPorNomeETurma(String nome, int turma) throws NotFoundException;
}
