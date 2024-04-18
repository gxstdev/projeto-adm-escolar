package tech.ada.school.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;
import tech.ada.school.domain.dto.AlunoDto;
import tech.ada.school.domain.dto.exception.NotFoundException;
import tech.ada.school.domain.entities.Aluno;
import tech.ada.school.external.FeignBoredApi;
import tech.ada.school.external.RestBoredApi;
import tech.ada.school.mappers.AlunoMapper;
import tech.ada.school.repositories.AlunoRepository;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class AlunoServiceBD implements IAlunoService{
    private final AlunoRepository repository;
    private final FeignBoredApi boredApi;
    @Override
    public AlunoDto criarAluno(AlunoDto aluno) {
        Aluno a = AlunoMapper.toEntity(aluno);
        return AlunoMapper.toDto(repository.save(a),boredApi.getActivity().activity());
    }

    @Override
    public List<AlunoDto> lerAlunos() {
        List<Aluno> alunos = repository.findAll();
        List<AlunoDto> alunosDto = new ArrayList<>();
        alunos.forEach(aluno -> alunosDto.add(AlunoMapper.toDto(aluno, boredApi.getActivity().activity())));
        return alunosDto;
    }

    @Override
    public AlunoDto buscarAlunoPorId(int id) throws NotFoundException {
        Aluno aluno = buscarAluno(id);
        return AlunoMapper.toDto(aluno, boredApi.getActivity().activity());
    }

    @Override
    public AlunoDto atualizarAluno(int id, AlunoDto aluno) throws NotFoundException {
        Aluno a = buscarAluno(id);
        a.setNome(aluno.getNome());
        a.setEmail(aluno.getEmail());
        a.setCpf(aluno.getCpf());
        a.setTurma(aluno.getTurma());
        return AlunoMapper.toDto(repository.save(a), boredApi.getActivity().activity());
    }

    @Override
    public void deletarAluno(int id) throws NotFoundException {
        Aluno a = buscarAluno(id);
        repository.delete(a);
    }

    @Override
    public List<AlunoDto> buscarAlunoPorTurma(int turma) throws NotFoundException {
        List<AlunoDto> alunoDtos = new ArrayList<>();
        List<Aluno> alunos = repository.findAllByTurma(turma);
        if (alunos.isEmpty()){
            throw new NotFoundException(AlunoDto.class, String.valueOf(turma));
        }
        alunos.forEach(aluno -> alunoDtos.add(AlunoMapper.toDto(aluno, boredApi.getActivity().activity())));
        return alunoDtos;
    }

    @Override
    public List<AlunoDto> buscarPorNomeETurma(String nome, int turma) throws NotFoundException {
        List<Aluno> alunos = repository.findAllByNomeAndTurma(nome, turma);
        List<AlunoDto> alunoDtos = new ArrayList<>();
        alunos.forEach(alunoEncontrado -> alunoDtos.add(AlunoMapper.toDto(alunoEncontrado, boredApi.getActivity().activity())));
        if (alunoDtos.isEmpty()){
            throw new NotFoundException(AlunoDto.class, String.valueOf(turma));
        }
        return alunoDtos;
    }

    public Aluno buscarAluno(int id) throws NotFoundException {
        Optional<Aluno> a = repository.findById(id);
        if (a.isPresent()){
            return a.get();
        }
        throw new NotFoundException(AlunoDto.class, String.valueOf(id));
    }
}
