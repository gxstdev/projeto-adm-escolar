package tech.ada.school.service;

import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.dto.ProfessorDto;

import java.util.List;

public interface IProfessorService {

    ProfessorDto criarProfessor(ProfessorDto professor);
    List<ProfessorDto> lerProfessores();
    ProfessorDto buscarProfessorPorId(int id) throws NotFoundException;
    ProfessorDto atualizarProfessor(int id, ProfessorDto professor) throws NotFoundException;
    void deletarProfessor(int id) throws NotFoundException;
    ProfessorDto buscarProfessorPorCpf(String cpf) throws NotFoundException;
    boolean verificarCpf(String cpf);
}
