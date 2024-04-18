package tech.ada.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tech.ada.school.domain.dto.ProfessorDto;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.entities.Professor;
import tech.ada.school.mappers.ProfessorMapper;
import tech.ada.school.repositories.ProfessorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProfessorServiceBD implements IProfessorService{
    private final ProfessorRepository repository;

    public ProfessorServiceBD(ProfessorRepository professorRepository) {
        this.repository = professorRepository;
    }
    @Override
    public ProfessorDto criarProfessor(ProfessorDto professor) {
        Professor p = ProfessorMapper.toEntity(professor);
        return ProfessorMapper.toDto(repository.save(p));
    }

    @Override
    public List<ProfessorDto> lerProfessores() {
        List<Professor> professores = repository.findAll();
        List<ProfessorDto> professorDtos = new ArrayList<>();

        professores.forEach(prof -> professorDtos.add(ProfessorMapper.toDto(prof)));

        return professorDtos;
    }

    @Override
    public ProfessorDto buscarProfessorPorId(int id) throws NotFoundException {
        Optional<Professor> p = repository.findById(id);
        if (p.isPresent()){
            return ProfessorMapper.toDto(p.get());
        }
        throw new NotFoundException(ProfessorDto.class, String.valueOf(id));
    }

    @Override
    public ProfessorDto atualizarProfessor(int id, ProfessorDto professor) throws NotFoundException {
        Professor p = buscarProfessor(id);
        p.setNome(professor.getNome());
        p.setCpf(professor.getCpf());
        p.setEmail(professor.getEmail());
        return ProfessorMapper.toDto(repository.save(p));
    }

    @Override
    public void deletarProfessor(int id) throws NotFoundException {
        Professor p = buscarProfessor(id);
        repository.delete(p);
    }
    public Professor buscarProfessor(int id) throws NotFoundException {
        Optional<Professor> p = repository.findById(id);
        if (p.isPresent()){
            return p.get();
        }
        throw new NotFoundException(ProfessorDto.class, String.valueOf(id));
    }
    public ProfessorDto buscarProfessorPorCpf(String cpf) throws NotFoundException {
        Optional<Professor> professor = repository.findByCpf(cpf);
        if (professor.isPresent()){
            return ProfessorMapper.toDto(professor.get());
        }
        throw new NotFoundException(ProfessorDto.class, cpf);
    }

    @Override
    public boolean verificarCpf(String cpf) {
        return false;
    }
}
