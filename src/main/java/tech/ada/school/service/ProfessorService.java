package tech.ada.school.service;

import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.dto.ProfessorDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorService implements IProfessorService{
    public ProfessorService() {
        System.out.println("Eu sou um bean.");
    }

    List<ProfessorDto> professores = new ArrayList<>();
    private int id = 1;

    @Override
    public ProfessorDto criarProfessor(ProfessorDto professor) {
        ProfessorDto result = new ProfessorDto(professor.getNome(), id++, professor.getCpf(), professor.getEmail());
        professores.add(result);
        return result;
    }

    @Override
    public List<ProfessorDto> lerProfessores() {
        return professores;
    }

    @Override
    public ProfessorDto buscarProfessorPorId(int id) throws NotFoundException {
        for (ProfessorDto professor : professores){
            if (professor.getId() == id){
                return professor;
            }
        }
        throw new NotFoundException(ProfessorDto.class, String.valueOf(id));
    }

    @Override
    public ProfessorDto atualizarProfessor(int id, ProfessorDto professor) throws NotFoundException {
        ProfessorDto prof = buscarProfessorPorId(id);
        prof.setNome(professor.getNome());
        prof.setCpf(professor.getCpf());
        prof.setEmail(professor.getEmail());
        return prof;
    }

    @Override
    public void deletarProfessor(int id) throws NotFoundException {
        ProfessorDto prof = buscarProfessorPorId(id);
        professores.remove(prof);
    }

    @Override
    public ProfessorDto buscarProfessorPorCpf(String cpf) throws NotFoundException {
        return null;
    }

    @Override
    public boolean verificarCpf(String cpf) {
        return false;
    }
}
